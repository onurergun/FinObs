package com.onurergun.finobsbe.application.user;

import com.onurergun.finobsbe.infrastructure.user.UserDao;

import java.util.List;

public interface UserApp {
    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers();

    UserDto getByUsername(String username);
}
