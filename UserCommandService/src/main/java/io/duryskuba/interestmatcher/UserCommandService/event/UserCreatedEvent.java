package io.duryskuba.interestmatcher.UserCommandService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class UserCreatedEvent {

    @TargetAggregateIdentifier
    private Long userId;
    private String username;
    private String password;
}
