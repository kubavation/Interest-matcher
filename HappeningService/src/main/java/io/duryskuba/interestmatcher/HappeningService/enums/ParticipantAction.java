package io.duryskuba.interestmatcher.HappeningService.enums;

import io.duryskuba.interestmatcher.HappeningService.resource.HappeningDTO;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningParticipantDTO;
import io.duryskuba.interestmatcher.HappeningService.service.HappeningService;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public enum ParticipantAction {

    ADD(HappeningService::incrementNumOfParticipants),
    REMOVE(HappeningService::decrementNumOfParticipants);

    private final BiConsumer<HappeningService, UUID> func;

    ParticipantAction(BiConsumer<HappeningService, UUID> func) {
        this.func = func;
    }

    public BiConsumer<HappeningService, UUID> getFunc() {
        return func;
    }
}
