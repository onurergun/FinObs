package com.onurergun.finobs.domain.user;

import com.onurergun.finobs.domain.user.dd.User;
import com.onurergun.finobs.domain.user.dd.Username;

import java.util.List;
import java.util.Optional;

public interface UserManager {

    void createUser(User user);

    List<User> getAllUsers();

    Optional<User> findUserByUsername(Username username);

    Optional<User> findUserByUsernameIgnoreCase(Username username);
}
