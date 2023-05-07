package com.onurergun.finobs.application.user;

import java.util.List;

public interface UserApp {
    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers();

    UserDto getByUsername(String username);
}
