package io.duryskuba.interestmatcher.UserService.converters;

import io.duryskuba.interestmatcher.UserService.resource.User;
import io.duryskuba.interestmatcher.UserService.resource.UserDto;

import java.util.ArrayList;
import java.util.Collection;

public class UserConverter {

    public static UserDto toDto(User user) {
        return UserDto.builder().build();
    }

    public static User toEntity(UserDto dto) {
        return new User();
    }

    public static Collection<User> toEntityCollection() {
        return new ArrayList<>();
    }

    public static Collection<UserDto> toDtoCollection() {
        return new ArrayList<>();
    }
}
