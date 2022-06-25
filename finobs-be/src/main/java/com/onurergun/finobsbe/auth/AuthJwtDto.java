package com.onurergun.finobsbe.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthJwtDto {

    private String userName;
    private String password;
}
