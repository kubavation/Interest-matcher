package io.duryskuba.interestmatcher.HappeningService.enums;

import io.duryskuba.interestmatcher.HappeningService.resource.HappeningDTO;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningParticipantDTO;
import io.duryskuba.interestmatcher.HappeningService.service.HappeningService;

import java.util.function.BiFunction;

public enum ParticipantAction {

    ADD(HappeningService::addParticipantToHappening),
    REMOVE(HappeningService::removeParticipantFromHappening);

    private BiFunction<HappeningService, HappeningParticipantDTO, HappeningParticipantDTO> func;

    ParticipantAction(BiFunction<HappeningService, HappeningParticipantDTO, HappeningParticipantDTO> func) {
        this.func = func;
    }
}
