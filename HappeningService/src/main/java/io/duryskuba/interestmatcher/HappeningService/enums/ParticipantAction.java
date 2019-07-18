package io.duryskuba.interestmatcher.HappeningService.enums;

import io.duryskuba.interestmatcher.HappeningService.resource.HappeningDTO;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningParticipantDTO;
import io.duryskuba.interestmatcher.HappeningService.service.HappeningService;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public enum ParticipantAction {

    ADD(HappeningService::addParticipantToHappeningv2),
    REMOVE(HappeningService::removeParticipantFromHappening);

    private final BiConsumer<HappeningService, HappeningParticipantDTO> func;

    ParticipantAction(BiConsumer<HappeningService, HappeningParticipantDTO> func) {
        this.func = func;
    }

    public BiConsumer<HappeningService, HappeningParticipantDTO> getFunc() {
        return func;
    }
}
