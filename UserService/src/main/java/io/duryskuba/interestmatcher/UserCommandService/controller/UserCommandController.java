package io.duryskuba.interestmatcher.UserCommandService.controller;

import io.duryskuba.interestmatcher.UserCommandService.command.CreateNewUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCommandController {

    @Autowired
    private CommandGateway commandGateway;


    @GetMapping("/users")
    public void createUser() {
        commandGateway.send(new CreateNewUserCommand(1L,"kuba","durys"));
    }
}
