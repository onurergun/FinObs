package com.onurergun.finobs.controller.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequest {
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
}
