package io.duryskuba.interestmatcher.UserCommandService.aggregate;

import io.duryskuba.interestmatcher.UserCommandService.command.CreateUserCommand;
import io.duryskuba.interestmatcher.UserCommandService.event.UserCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class User {

    @AggregateIdentifier
    private Long userId;
    private String username;
    private String password;


    public User() {}

    @CommandHandler
    public User(CreateUserCommand cmd) {
        System.out.println("??");
        apply(new UserCreatedEvent(cmd.getUserId(), cmd.getUsername(), cmd.getPassword()));
    }


    @EventSourcingHandler
    public void handeUserCreation(UserCreatedEvent event) {
        System.out.println("here");
        userId = event.getUserId();
        username = event.getUsername();
        password = event.getPassword();
    }
}
