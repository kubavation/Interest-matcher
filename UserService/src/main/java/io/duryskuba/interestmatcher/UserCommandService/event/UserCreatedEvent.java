package io.duryskuba.interestmatcher.UserCommandService.event;


import lombok.Data;

@Data
public class UserCreatedEvent {

    private final Long userId;
    private final String username;
    private final String password;
}
