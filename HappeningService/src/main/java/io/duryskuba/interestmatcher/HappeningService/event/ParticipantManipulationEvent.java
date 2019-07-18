package io.duryskuba.interestmatcher.HappeningService.event;

import io.duryskuba.interestmatcher.HappeningService.enums.ParticipantAction;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Getter
public class ParticipantManipulationEvent extends ApplicationEvent {

    private final ParticipantAction action;

    public ParticipantManipulationEvent(ParticipantAction action, Object source) {
        super(source);
        this.action = action;
    }
}
