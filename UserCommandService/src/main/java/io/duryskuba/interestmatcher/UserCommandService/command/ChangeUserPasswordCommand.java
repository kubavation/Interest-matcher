package io.duryskuba.interestmatcher.UserCommandService.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ChangeUserPasswordCommand {

    @TargetAggregateIdentifier
    private final String userId;
    private final String password;
}
