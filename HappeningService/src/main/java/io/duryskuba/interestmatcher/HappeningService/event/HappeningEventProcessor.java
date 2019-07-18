package io.duryskuba.interestmatcher.HappeningService.event;

import io.duryskuba.interestmatcher.HappeningService.service.HappeningService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class HappeningEventProcessor {

    private HappeningService happeningService;
    private ApplicationEventPublisher applicationEventPublisher;

    public HappeningEventProcessor(HappeningService happeningService,
                                   ApplicationEventPublisher applicationEventPublisher) {
        this.happeningService = happeningService;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    public void manipulateParticipants(ParticipantManipulationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
