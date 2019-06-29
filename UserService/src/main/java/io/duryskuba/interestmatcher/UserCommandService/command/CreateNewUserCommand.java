package io.duryskuba.interestmatcher.UserCommandService.command;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
public class CreateNewUserCommand {

    @TargetAggregateIdentifier
    private final Long userId;
    private final String username;
    private final String password;

    public CreateNewUserCommand(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }
}
