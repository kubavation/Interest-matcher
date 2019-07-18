package io.duryskuba.interestmatcher.HappeningService.service;

import io.duryskuba.interestmatcher.HappeningService.enums.ParticipantAction;
import io.duryskuba.interestmatcher.HappeningService.event.HappeningEventProcessor;
import io.duryskuba.interestmatcher.HappeningService.event.ParticipantManipulationEvent;
import io.duryskuba.interestmatcher.HappeningService.exception.HappeningNotAvailableException;
import io.duryskuba.interestmatcher.HappeningService.exception.ResourceNotFoundException;
import io.duryskuba.interestmatcher.HappeningService.repository.HappeningParticipantRepository;
import io.duryskuba.interestmatcher.HappeningService.repository.HappeningRepository;
import io.duryskuba.interestmatcher.HappeningService.resource.*;
import io.duryskuba.interestmatcher.HappeningService.util.HappeningConverter;
import io.duryskuba.interestmatcher.HappeningService.util.HappeningParticipantConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

import static io.duryskuba.interestmatcher.HappeningService.enums.ParticipantAction.ADD;
import static io.duryskuba.interestmatcher.HappeningService.enums.ParticipantAction.REMOVE;
import static io.duryskuba.interestmatcher.HappeningService.event.ParticipantManipulationEvent.of;
import static io.duryskuba.interestmatcher.HappeningService.util.HappeningConverter.toEntity;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Service
@Slf4j
public class HappeningService {

    private HappeningRepository happeningRepository;
    private WebClient webClient;
    private HappeningParticipantRepository happeningParticipantRepository;
    private HappeningEventProcessor eventProcessor;

    public HappeningService(HappeningRepository happeningRepository, WebClient webClient,
                            HappeningParticipantRepository happeningParticipantRepository,
                            HappeningEventProcessor eventProcessor) {
        this.happeningRepository = happeningRepository;
        this.webClient = webClient;
        this.happeningParticipantRepository = happeningParticipantRepository;
        this.eventProcessor = eventProcessor;
    }

    public Collection<Happening> findAll() {
        return happeningRepository.findAll();
    }

    public Happening findById(String id) {
        return happeningRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Happening create(HappeningDTO happeningDTO) {
        happeningDTO = setLocationOfHappening(happeningDTO);
        return happeningRepository.save(toEntity(happeningDTO));
    }


    public HappeningDTO setLocationOfHappening(HappeningDTO dto) {
        LocationDTO location = webClient
                .post()
                .uri("http://location-service/locations")
                .body(fromObject(dto.getLocation()))
                .exchange()
                    .block()
                    .toEntity(LocationDTO.class)
                    .block().getBody();

        HappeningDTO result = new HappeningDTO(dto);
        result.setLocation(location);
        return result;
    }

    public void removeParticipantFromHappening(String happeningId, String participantId) {

        HappeningParticipant participant =
                happeningParticipantRepository
                .findFirstByHappeningIdAndParticipantId(happeningId, participantId)
                //.map() todo delete
                .orElseThrow(ResourceNotFoundException::new);

        happeningParticipantRepository.deleteById(participant.getParticipantId()); //todo move to stream
        eventProcessor.publishParticipantManipulationEvent( of(REMOVE, happeningId, this) );
    }


    @EventListener
    public void manipulateHappeningParticipants(ParticipantManipulationEvent event) {
        log.info(event.toString());
        event.getAction().getFunc().accept(this, event.getHappeningId());
    }

    @Transactional
    public HappeningParticipantDTO addParticipantToHappening(HappeningParticipantDTO participant) {
        return HappeningParticipantConverter.toDTO( saveParticipantIfHappeningAvailable(participant) );
    }


    public HappeningParticipant saveParticipantIfHappeningAvailable(HappeningParticipantDTO participant) {
        return happeningRepository.findById(participant.getHappeningId())
                .filter(this::isHappeningAvailable)
                .map(h -> {
                   eventProcessor
                           .publishParticipantManipulationEvent( of(ADD, h.getId(), this) );
                   return happeningParticipantRepository
                            .save(HappeningParticipantConverter.toEntity(participant));
                })
                    .orElseThrow(() -> new ResourceNotFoundException(participant.getHappeningId()));
    }


    public boolean isHappeningAvailable(Happening happening) {

        if(!inHappeningParticipantRange(happening,getNumOfActualParticipants(happening.getId())))
            throw new HappeningNotAvailableException(happening.getId());

        return true;
    }


    public Long getNumOfActualParticipants(String happeningId) {
        return happeningParticipantRepository.countAllByHappeningId(happeningId);
    }

    public boolean inHappeningParticipantRange(Happening happening, Long present) {
        return present < happening.getMaxNumberOfParticipants();
    }


    /**
    *   Consumer methods for enum ParticipantAction
    **/

    public void incrementNumOfParticipants(String happeningId) {
        happeningRepository.findById(happeningId)
                .ifPresent(t -> t.setActualNumOfParticipants(t.getActualNumOfParticipants() + 1));
    }

    public void decrementNumOfParticipants(String happeningId) {
        happeningRepository.findById(happeningId)
                .ifPresent(t -> t.setActualNumOfParticipants(t.getActualNumOfParticipants() - 1));
    }



}

