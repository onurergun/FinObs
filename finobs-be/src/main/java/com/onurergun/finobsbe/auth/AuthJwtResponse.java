package com.onurergun.finobsbe.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthJwtResponse {

    private String token;

    private String userName;
}
