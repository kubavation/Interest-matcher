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
    private final HappeningParticipantDTO dto;

    public ParticipantManipulationEvent(ParticipantAction action,
                                        HappeningParticipantDTO dto,
                                        Object source) {
        super(source);
        this.dto = dto;
        this.action = action;
    }
}
