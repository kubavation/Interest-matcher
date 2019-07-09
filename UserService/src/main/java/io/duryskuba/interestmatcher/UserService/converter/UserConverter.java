package io.duryskuba.interestmatcher.UserService.converter;

import io.duryskuba.interestmatcher.UserService.resource.User;
import io.duryskuba.interestmatcher.UserService.resource.UserDto;
import org.springframework.data.domain.Pageable;
import sun.jvm.hotspot.debugger.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UserConverter {

    public static UserDto toDto(User user) {
        return
                UserDto.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .userId(user.getId())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .build();
    }

    public static User toEntity(UserDto dto) {
        return
                User.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .id(dto.getUserId())
                    .username(dto.getUsername())
                    .password(dto.getPassword())
                    .email(dto.getEmail())
                    .build();
    }

    public static Collection<User> toEntityCollection(Collection<UserDto> dtos) {
        return dtos.stream()
                    .map(UserConverter::toEntity)
                    .collect(toList());
    }

    public static Collection<UserDto> toDtoCollection(Collection<User> users) {
        return users.stream()
                    .map(UserConverter::toDto)
                    .collect(toList());
    }

}
