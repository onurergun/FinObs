package com.onurergun.finobsbe.domain.user;

import com.onurergun.finobsbe.domain.user.dd.User;
import com.onurergun.finobsbe.domain.user.dd.Username;

import java.util.List;
import java.util.Optional;

public interface UserManager {

    void createUser(User user);

    List<User> getAllUsers();

    Optional<User> findUserByUsername(Username username);

    Optional<User> findUserByUsernameIgnoreCase(Username username);
}
