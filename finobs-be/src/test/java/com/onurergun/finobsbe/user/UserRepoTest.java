package com.onurergun.finobsbe.user;

import com.onurergun.finobsbe.infrastructure.user.UserDao;
import com.onurergun.finobsbe.infrastructure.user.UserJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserJpaRepository repository;

    @Test
    public void createUser() {
        UserDao user1 = new UserDao();
        user1.setUserId(1L);
        user1.setFirstName("FN1");
        user1.setLastName("LN1");
        user1.setUserName("UN1");
        user1.setEmail("email1@email.com");
        user1.setPassword(new BCryptPasswordEncoder().encode("password"));

        repository.save(user1);

        assertThat(repository.count() > 0).isTrue();
    }
}
