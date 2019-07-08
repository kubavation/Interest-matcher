package io.duryskuba.interestmatcher.HappeningService.service;

import io.duryskuba.interestmatcher.HappeningService.repository.HappeningRepository;
import io.duryskuba.interestmatcher.HappeningService.resource.Happening;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningDTO;
import io.duryskuba.interestmatcher.HappeningService.resource.LocationDTO;
import io.duryskuba.interestmatcher.HappeningService.util.HappeningConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;
import java.util.UUID;

import static io.duryskuba.interestmatcher.HappeningService.util.HappeningConverter.toEntity;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Service
public class HappeningService {

    private HappeningRepository happeningRepository;
    private WebClient webClient;

    public HappeningService(HappeningRepository happeningRepository, WebClient webClient) {
        this.happeningRepository = happeningRepository;
        this.webClient = webClient;
    }

    public Collection<Happening> findAll() {
        return happeningRepository.findAll();
    }

    public Happening findById(UUID id) {
        return happeningRepository.findById(id)
                    .orElseThrow(RuntimeException::new); //resourcenotfound
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

        //todo + exception
        System.out.println(location);
        HappeningDTO result = new HappeningDTO(dto);
        result.setLocation(location);

        System.out.println(result);
        return result;
    }

}

