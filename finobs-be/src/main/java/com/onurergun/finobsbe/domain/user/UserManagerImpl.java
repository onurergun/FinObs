package com.onurergun.finobsbe.domain.user;

import com.onurergun.finobsbe.domain.user.dd.User;
import com.onurergun.finobsbe.domain.user.dd.Username;

import java.util.List;
import java.util.Optional;

class UserManagerImpl implements UserManager {

    private final UserRepository userRepository;

    public UserManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        userRepository.createOrUpdate(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    public Optional<User> findUserByUsername(Username username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByUsernameIgnoreCase(Username username) {
        return userRepository.findUserByUsernameIgnoreCase(username);
    }
}
