package com.onurergun.finobs.application.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.onurergun.finobs.domain.user.UserManager;
import com.onurergun.finobs.domain.user.dd.*;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserAppImplTest {

    private UserAppImpl userApp;

    @Mock
    private UserManager userManager;

    @Mock
    private UserDto userDto;

    @Mock
    private User user;

    private static final String USER_FIRSTNAME = "FIRSTNAME";
    private static final String USER_LASTNAME = "LASTNAME";
    private static final String USER_USERNAME = "USERNAME";
    private static final String USER_EMAIL = "EMAIL@EMAIL.COM";

    private static final String PASSWORD_PLAIN = "PASS_PLAIN";
    private static final String PASSWORD_ENCODED = "PASS_ENCODED";


    @BeforeEach
    void setup() {
        userApp = new UserAppImpl(userManager);

        Firstname mockFirstname = mock(Firstname.class);
        Lastname mockLastname = mock(Lastname.class);
        Username mockUsername = mock(Username.class);
        Email mockEmail = mock(Email.class);
        Password mockPassword = mock(Password.class);

        lenient().when(userDto.to()).thenReturn(user);
        lenient().when(userDto.getFirstName()).thenReturn(USER_FIRSTNAME);
        lenient().when(userDto.getLastName()).thenReturn(USER_LASTNAME);
        lenient().when(userDto.getUserName()).thenReturn(USER_USERNAME);
        lenient().when(userDto.getEmail()).thenReturn(USER_EMAIL);
        lenient().when(userDto.getPassword()).thenReturn(PASSWORD_PLAIN);

        lenient().when(user.getFirstname()).thenReturn(mockFirstname);
        lenient().when(mockFirstname.value()).thenReturn(USER_FIRSTNAME);

        lenient().when(user.getLastname()).thenReturn(mockLastname);
        lenient().when(mockLastname.value()).thenReturn(USER_LASTNAME);

        lenient().when(user.getUsername()).thenReturn(mockUsername);
        lenient().when(mockUsername.value()).thenReturn(USER_USERNAME);

        lenient().when(user.getEmail()).thenReturn(mockEmail);
        lenient().when(mockEmail.value()).thenReturn(USER_EMAIL);

        lenient().when(user.getPassword()).thenReturn(mockPassword);
        lenient().when(mockPassword.value()).thenReturn(PASSWORD_ENCODED);
    }

    @Test
    void givenValidUserDto_whenCreateUser_thenReturnCreatedUser() {
        // given

        // when
        UserDto createdUser = assertDoesNotThrow( ()-> userApp.createUser(userDto));

        // then
        assertNotNull(createdUser);
        verify(userDto, times(1)).encodePassword(any());
        verify(userDto, times(1)).to();
        verify(userManager, times(1)).createUser(user);
        assertEquals(userDto.getFirstName(), createdUser.getFirstName());
        assertEquals(userDto.getLastName(), createdUser.getLastName());
        assertEquals(userDto.getUserName(), createdUser.getUserName());
        assertEquals(userDto.getEmail(), createdUser.getEmail());
        assertNotEquals(userDto.getPassword(), createdUser.getPassword());
    }

    @Test
    void givenNothing_whenGetAllUsers_thenReturnListOfUsers() {
        // given
        when(userManager.getAllUsers()).thenReturn(List.of(user));

        // when
        List<UserDto> userDtos = assertDoesNotThrow(() -> userApp.getAllUsers());

        // then
        assertNotNull(userDtos);
        assertEquals(1, userDtos.size());
    }

    @Test
    void givenValidUsernameExists_whenGetByUsername_thenReturnUserDto() {
        // given
        when(userManager.findUserByUsernameIgnoreCase(any())).thenReturn(Optional.of(user));

        // when
        UserDto foundUserDto = assertDoesNotThrow(() -> userApp.getByUsername(USER_USERNAME));

        // then
        assertNotNull(foundUserDto);
        assertEquals(USER_USERNAME, foundUserDto.getUserName());
        verify(userManager, times(1)).findUserByUsernameIgnoreCase(any());
    }


    @Test
    void givenValidUsernameDoesNotExist_whenGetByUsername_thenReturnUserDto() {
        // given
        when(userManager.findUserByUsernameIgnoreCase(any())).thenReturn(Optional.empty());

        // when
        UserDto foundUserDto = assertDoesNotThrow(() -> userApp.getByUsername(USER_USERNAME));

        // then
        assertNull(foundUserDto);
        verify(userManager, times(1)).findUserByUsernameIgnoreCase(any());
    }
}
