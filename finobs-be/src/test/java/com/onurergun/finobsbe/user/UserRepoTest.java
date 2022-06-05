package com.onurergun.finobsbe.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void createUser() {
        User user1 = new User();
        user1.setUserId(1L);
        user1.setFirstName("FN1");
        user1.setLastName("LN1");
        user1.setUserName("UN1");
        user1.setEmail("email1@email.com");

        repository.save(user1);

        assertThat(repository.count() > 0).isTrue();
    }
}
