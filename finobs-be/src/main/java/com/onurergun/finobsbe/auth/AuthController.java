package com.onurergun.finobsbe.auth;

import com.onurergun.finobsbe.common.APIResponseBody;
import com.onurergun.finobsbe.common.APIResponseBodyError;
import com.onurergun.finobsbe.common.AbstractAPIResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "${finobs.api-prefix}" + "auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/jwt")
    public ResponseEntity<AbstractAPIResponseBody> loginJwt(
            HttpServletRequest request,
            @RequestBody AuthJwtDto authJwtDto) {
        try {
            final String username = authJwtDto.getUserName();
            final String password = authJwtDto.getPassword();

            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authJwtDto.getUserName());
            final String jwtToken = jwtUtil.generateToken(userDetails);

            APIResponseBody responseBody = new APIResponseBody(request.getServletPath());
            responseBody.setMessage("Login successful");
            fillResponseBodyRequestData(responseBody, username, password);

            final AuthJwtResponse jwtResponse = new AuthJwtResponse(jwtToken, username);
            fillResponseBodyResponseData(responseBody, jwtResponse);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);

        } catch (AuthenticationException e) {
            APIResponseBodyError errorBody = new APIResponseBodyError(request.getServletPath());
            errorBody.setMessage("Invalid username or password");
            return new ResponseEntity<>(errorBody, HttpStatus.UNAUTHORIZED);
        }
    }

    private void fillResponseBodyRequestData(APIResponseBody responseBody, String username, String password) {
        responseBody.addUpdateRequestDataParam("username", username);
        responseBody.addUpdateRequestDataParam("password", password);
    }

    private void fillResponseBodyResponseData(APIResponseBody responseBody, AuthJwtResponse jwtResponse) {
        responseBody.setResponseData(jwtResponse);
    }
}
