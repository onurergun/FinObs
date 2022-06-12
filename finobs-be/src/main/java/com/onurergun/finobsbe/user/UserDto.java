package com.onurergun.finobsbe.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(value = "password", allowSetters = true)
public class UserDto {
    @NotBlank
    @Size(min = 1, max = 255)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 255)
    private String lastName;

    @NotBlank
    @Size(min = 1, max = 255)
    private String userName;

    @NotBlank
    @Size(min = 1, max = 255)
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}].:;',?/*~$^+=<>]).{8,255}$",
    message = "Must minimum 8 characters length: At least 1 numeric, 1 special character and 1 uppercase")
    private String password;

    private Date registerDate;
}
