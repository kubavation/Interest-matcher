package io.duryskuba.interestmatcher.UserService.controller;

import io.duryskuba.interestmatcher.UserService.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/user/{id}/posts")
    public ResponseEntity<?> findUserPosts(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
