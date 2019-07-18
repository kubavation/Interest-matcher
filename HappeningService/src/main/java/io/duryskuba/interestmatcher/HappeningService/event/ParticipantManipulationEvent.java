package io.duryskuba.interestmatcher.HappeningService.event;

import io.duryskuba.interestmatcher.HappeningService.enums.ParticipantAction;
import io.duryskuba.interestmatcher.HappeningService.resource.HappeningParticipantDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Getter
public class ParticipantManipulationEvent extends ApplicationEvent {

    private final ParticipantAction action;
    private final String happeningId;

    public ParticipantManipulationEvent(ParticipantAction action,
                                        String happeningId,
                                        Object source) {
        super(source);
        this.happeningId = happeningId;
        this.action = action;
    }
}
