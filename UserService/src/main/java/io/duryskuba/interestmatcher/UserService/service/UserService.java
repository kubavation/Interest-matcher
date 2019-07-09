package io.duryskuba.interestmatcher.UserService.service;

import io.duryskuba.interestmatcher.UserService.converter.UserConverter;
import io.duryskuba.interestmatcher.UserService.repository.UserRepository;
import io.duryskuba.interestmatcher.UserService.resource.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        return this.userRepository.findAll(PageRequest.of(from, offset))
                    .map(UserConverter::toDto);
    }

    public 

}
