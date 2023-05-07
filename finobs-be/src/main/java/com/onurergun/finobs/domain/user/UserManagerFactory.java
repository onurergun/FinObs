package com.onurergun.finobs.domain.user;

public class UserManagerFactory {

    public static UserManager create(UserRepository userRepository) {
        return new UserManagerImpl(userRepository);
    }
}
