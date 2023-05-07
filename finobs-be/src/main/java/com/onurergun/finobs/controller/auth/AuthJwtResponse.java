package com.onurergun.finobs.controller.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class AuthJwtResponse {

    private String token;

    private String userName;
}
