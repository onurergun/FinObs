package com.onurergun.finobsbe.application.auth;

import com.onurergun.finobsbe.infrastructure.auth.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
class AuthAppImpl implements AuthApp {
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    public AuthAppImpl(AuthenticationManager authenticationManager,
                       FinObsUserDetailsService userDetailsService,
                       JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String authenticateWithUsernameAndPassword(String username, String password) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwtToken = jwtUtil.generateToken(userDetails);

        return jwtToken;
    }
}
