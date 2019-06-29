package io.duryskuba.interestmatcher.UserCommandService.controller;

import io.duryskuba.interestmatcher.UserCommandService.command.ChangeUserPasswordCommand;
import io.duryskuba.interestmatcher.UserCommandService.command.CreateUserCommand;
import io.duryskuba.interestmatcher.UserCommandService.event.UserChangedPasswordEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.UUID;

@RestController
public class UserCommandController {

    @Autowired
    CommandGateway commandGateway;

    @Autowired
    private EventStore eventStore;

    @PostConstruct
    public void x( ) {
        System.out.println("XDD");
    }

    @PostMapping("/user")
    public void test() {
        System.out.println("in controller");
        UUID xd = UUID.randomUUID();
        System.out.println(xd.toString());
        commandGateway.send(new CreateUserCommand(xd.toString(),"kuba","durys"));
    }

    @GetMapping("/user/{id}/{pass}")
    public void change(@PathVariable("pass") String pass, @PathVariable("id") String id) {
        System.out.println("in pass");
        System.out.println("ID " + id);
        commandGateway.send(new ChangeUserPasswordCommand(id, pass));
    }

    @PostMapping("/user2/{id}")
    public void test2(@PathVariable String id) {
        System.out.println("in post2");
        commandGateway.send(new CreateUserCommand(id,"kuba"+id,"durys"));
    }

    @GetMapping("/get/{id}")
    public void xd(@PathVariable String id) {
        System.out.println("in xd");
        eventStore.readEvents(id).asStream().forEach(System.out::println);
    }
}
