package io.duryskuba.interestmatcher.UserCommandService.controller;

import io.duryskuba.interestmatcher.UserCommandService.command.CreateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCommandController {

    @Autowired
    CommandGateway commandGateway;

    @GetMapping("/user")
    public void test() {
        System.out.println("in controller");
        commandGateway.send(new CreateUserCommand(1L,"kuba","durys"));
    }
}
