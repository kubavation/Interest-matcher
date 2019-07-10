package io.duryskuba.interestmatcher.UserService.controller;

import io.duryskuba.interestmatcher.UserService.resource.UserDto;
import io.duryskuba.interestmatcher.UserService.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * for testing sleuth purposes
     */
    @GetMapping("/")
    public void test() {
        log.info("HELLO SLEUTH");
    }

    //@RequestHeader(name = "test") String test
    @GetMapping("/users")
    public ResponseEntity<?> findAll() {


        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users/from/{from}/offset/{offset}")
    public ResponseEntity<?> findAllWithPagination(@PathVariable("from") int from,
                                                   @PathVariable("offset") int offset) {
        return new ResponseEntity<>(userService.findAll(from, offset), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.create(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable Long id) {
        return new ResponseEntity<>(userService.update(userDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
