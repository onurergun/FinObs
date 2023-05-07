package com.onurergun.finobsbe.user;

import com.onurergun.finobsbe.application.user.UserServiceImpl;
import com.onurergun.finobsbe.controller.user.UserController;
import com.onurergun.finobsbe.infrastructure.user.UserDtoMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

  private MockMvc mockMvc;

  @MockBean
  private UserDtoMapper mapper;

  @MockBean
  private UserServiceImpl service;

//  @Test
//  public void UsersGet() throws Exception {
//
//    List<User> mockUsers = new ArrayList<User>();
//    User user1 = new User();
//    user1.setUserId(1L);
//    user1.setFirstName("FN1");
//    user1.setLastName("LN1");
//    user1.setUserName("UN1");
//    user1.setEmail("email1@email.com");
//    user1.setPassword("Password1*");
//
//    User user2 = new User();
//    user2.setUserId(2L);
//    user2.setFirstName("FN2");
//    user2.setLastName("LN2");
//    user2.setUserName("UN2");
//    user2.setEmail("email2@email.com");
//    user2.setPassword("Password2*");
//
//    mockUsers.add(user1);
//    mockUsers.add(user2);
//
//    when(service.getAllUsers()).thenReturn(mockUsers);
//
//    mockMvc.perform(get("/api/v1/users"))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType("application/json"));
//  }
}
