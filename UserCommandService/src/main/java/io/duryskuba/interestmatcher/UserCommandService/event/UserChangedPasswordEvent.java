package io.duryskuba.interestmatcher.UserCommandService.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class UserChangedPasswordEvent {

    @TargetAggregateIdentifier
    private String userId;
    private String password;
}
