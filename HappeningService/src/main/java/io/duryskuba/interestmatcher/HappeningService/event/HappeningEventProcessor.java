package io.duryskuba.interestmatcher.HappeningService.event;

import io.duryskuba.interestmatcher.HappeningService.service.HappeningService;
import org.springframework.stereotype.Component;

@Component
public class HappeningEventProcessor {

    private HappeningService happeningService;

    public HappeningEventProcessor(HappeningService happeningService) {
        this.happeningService = happeningService;
    }


    public void manipulateParticipants()
}
