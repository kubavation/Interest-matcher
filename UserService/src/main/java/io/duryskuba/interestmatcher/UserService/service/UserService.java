package io.duryskuba.interestmatcher.UserService.service;

import io.duryskuba.interestmatcher.UserService.converter.UserConverter;
import io.duryskuba.interestmatcher.UserService.enums.AccountStatus;
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



    public Collection<UserDto> findAll() {
        return new ArrayList<>();
    }

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

    public User findById(Long id) {
        return userRepository.findById(id)
                    .orElseThrow(RuntimeException::new);
    }

    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserConverter::toDto);
    }

    public UserDto findByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                .map(UserConverter::toDto)
                .orElseThrow(RuntimeException::new); //resourcenotfound
    }

    @Transactional
    public UserDto create(UserDto userDto) {
        assertIfUserIsAvailable(userDto);
        return toDto(userRepository.save(toEntity(userDto)));
    }

    @Transactional
    public UserDto update(UserDto userDto, Long userId) {
        userDto.setUserId(findById(userId).getId());
        return toDto (
                userRepository
                    .save(toEntity(userDto)) );
    }

    public void delete(Long userId) {
        User existing = findById(userId);
        existing.setStatus(AccountStatus.DELETED);
        userRepository.save(existing);
    }


    public void assertIfUserIsAvailable(UserDto userDto) {

        findByUsername(userDto.getUsername())
                .ifPresent(u -> {
                    throw new RuntimeException("todo usernametakenexception");
                });

        findByEmail(userDto.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("todo emailtakenexception");
                });

    }

}
