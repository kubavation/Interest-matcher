package io.duryskuba.interestmatcher.UserCommandService.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CreateUserCommand {

    @TargetAggregateIdentifier
    private final Long userId;
    private final String username;
    private final String password;


}
