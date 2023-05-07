package com.onurergun.finobs.application.user;

import java.util.Date;

import com.onurergun.finobs.domain.user.dd.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Date registerDate;
    private boolean isPasswordEncoded;

    public UserDto(Date registerDate) {
        this.registerDate = registerDate;
    }


    /**
     * Be aware that this function is NOT thread-safe and may problematic
     * in multi-thread environment. It aims to encode the password once with
     * the given password encoder.
     * @param passwordEncoder
     */
    public void encodePassword(PasswordEncoder passwordEncoder) {

        if (isPasswordEncoded)
            return;

        password = passwordEncoder.encode(password);
        isPasswordEncoded = true;
    }

    public User to() {
        return new User(
                new Firstname(getFirstName()),
                new Lastname(getLastName()),
                new Username(getUserName()),
                new Email(getEmail()),
                new Password(getPassword())
        );
    }

    public static UserDto from(User user) {
        return new UserDto(
                user.getFirstname().value(),
                user.getLastname().value(),
                user.getUsername().value(),
                user.getPassword().value(),
                user.getEmail().value(),
                user.getRegisterDate(),
                true
        );
    }
}
