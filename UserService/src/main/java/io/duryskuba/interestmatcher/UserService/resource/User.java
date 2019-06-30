package io.duryskuba.interestmatcher.UserService.resource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
}
