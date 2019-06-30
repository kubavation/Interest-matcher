package io.duryskuba.interestmatcher.UserCommandService.aggregate;

import io.duryskuba.interestmatcher.UserCommandService.command.CreateNewUserCommand;
import io.duryskuba.interestmatcher.UserCommandService.event.UserCreatedEvent;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;


@Aggregate
public class User {

    @AggregateIdentifier
    private Long userId;

    private String username;
    private String password;

    public User() {

    }


    @CommandHandler
    public User(CreateNewUserCommand cmd) {
        apply(new UserCreatedEvent(cmd.getUserId(), cmd.getUsername(), cmd.getPassword()));
    }



    @EventSourcingHandler
    private void handeCreatedUserEvent(UserCreatedEvent event) {
        System.out.println("here");
        userId = event.getUserId();
        username = event.getUsername();
        password = event.getPassword();
    }
}
