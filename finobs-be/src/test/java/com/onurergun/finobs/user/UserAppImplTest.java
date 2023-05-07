package com.onurergun.finobs.user;

import com.onurergun.finobs.application.user.UserServiceImpl;
import com.onurergun.finobs.infrastructure.user.UserDao;
import com.onurergun.finobs.infrastructure.user.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserAppImplTest {

  @Mock
  private UserJpaRepository repo;

  private UserServiceImpl service;

  @BeforeEach
  private void Setup() {
    service = new UserServiceImpl(repo);
  }

  @Test
  public void createUser() {
    final Long userId = 1L;

    UserDao user1 = new UserDao();
    user1.setUserId(userId);
    user1.setFirstName("FN1");
    user1.setLastName("LN1");
    user1.setUserName("UN1");
    user1.setEmail("email1@email.com");
    user1.setPassword("Password1");

    when(repo.save(user1)).thenReturn(user1);
    assertThat(user1.getUserId() == service.createUser(user1).getUserId()).isTrue();
  }
}
