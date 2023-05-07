package com.onurergun.finobsbe.infrastructure.config;

import com.onurergun.finobsbe.domain.user.UserManager;
import com.onurergun.finobsbe.domain.user.UserManagerFactory;
import com.onurergun.finobsbe.domain.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanInitializer {

    private final UserRepository userRepository;

    public BeanInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    UserManager createUserManager() {
        return UserManagerFactory.create(userRepository);
    }

}
