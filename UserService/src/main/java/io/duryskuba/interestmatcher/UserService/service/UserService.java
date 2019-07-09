package io.duryskuba.interestmatcher.UserService.service;

import io.duryskuba.interestmatcher.UserService.converter.UserConverter;
import io.duryskuba.interestmatcher.UserService.repository.UserRepository;
import io.duryskuba.interestmatcher.UserService.resource.User;
import io.duryskuba.interestmatcher.UserService.resource.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static io.duryskuba.interestmatcher.UserService.converter.UserConverter.toDto;
import static io.duryskuba.interestmatcher.UserService.converter.UserConverter.toEntity;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @return list of all users
     */
    public Collection<UserDto> findAll() {
        return new ArrayList<>();
    }

    /**
     * @param from
     * @param offset
     *
     * @return page of users
     */
    public Page<UserDto> findAll(int from, int offset) {
        return userRepository.findAll(PageRequest.of(from, offset))
                    .map(UserConverter::toDto);
    }

    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                    .map(UserConverter::toDto);
    }

    public UserDto findByUsernameOrThrow(String username) {
        return userRepository.findByUsername(username)
                    .map(UserConverter::toDto)
                    .orElseThrow(RuntimeException::new); //resourcenotfound
    }

    @Transactional
    public UserDto create(UserDto userDto) {
        assertIfUserIsAvailable(userDto);
        return toDto(userRepository.save(toEntity(userDto)));
    }

    public void assertIfUserIsAvailable(UserDto userDto) {
        findByUsername(userDto.getUsername())
                .ifPresent(u -> {
                    throw new RuntimeException("todo usernametakenexception");
                });
    }

}
