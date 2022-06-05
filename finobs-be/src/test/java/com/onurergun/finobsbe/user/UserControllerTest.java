package com.onurergun.finobsbe.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService service;

  @Test
  public void UsersGet() throws Exception {

    List<User> mockUsers = new ArrayList<User>();
    User user1 = new User();
    user1.setUserId(1L);
    user1.setFirstName("FN1");
    user1.setLastName("LN1");
    user1.setUserName("UN1");
    user1.setEmail("email1@email.com");
    user1.setPassword("Password1");

    User user2 = new User();
    user2.setUserId(2L);
    user2.setFirstName("FN2");
    user2.setLastName("LN2");
    user2.setUserName("UN2");
    user2.setEmail("email2@email.com");
    user2.setPassword("Password2");

    mockUsers.add(user1);
    mockUsers.add(user2);

    when(service.getAllUsers()).thenReturn(mockUsers);

    mockMvc.perform(get("/api/v1/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"));
  }
}
