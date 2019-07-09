package io.duryskuba.interestmatcher.UserService.resource;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
