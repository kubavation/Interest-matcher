package io.duryskuba.interestmatcher.UserService.resource;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class UserDto {

    private Long userId;
    private String username;
}
