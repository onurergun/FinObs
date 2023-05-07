package com.onurergun.finobsbe.controller.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthJwtRequest {

    private String userName;
    private String password;
}
