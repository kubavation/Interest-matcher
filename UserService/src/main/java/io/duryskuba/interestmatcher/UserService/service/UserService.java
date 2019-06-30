package io.duryskuba.interestmatcher.UserService.service;

import io.duryskuba.interestmatcher.UserService.resource.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    /**
     * method for retrieving users
     * @return list of all users
     */
    public List<UserDto> findAll() {
        return new ArrayList<>();
    }


}
