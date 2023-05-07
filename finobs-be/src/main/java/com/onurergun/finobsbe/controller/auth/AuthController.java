package com.onurergun.finobsbe.controller.auth;

import com.onurergun.finobsbe.application.auth.AuthApp;
import com.onurergun.finobsbe.controller.common.APIResponseBody;
import com.onurergun.finobsbe.controller.common.APIResponseBodyError;
import com.onurergun.finobsbe.controller.common.AbstractAPIResponseBody;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${finobs.api-prefix}" + "auth")
public class AuthController {

    private final AuthApp authApp;

    public AuthController(AuthApp authApp) {
        this.authApp = authApp;
    }

    @PostMapping("/jwt")
    public ResponseEntity<AbstractAPIResponseBody> loginJwt(
            HttpServletRequest request,
            @RequestBody AuthJwtRequest authJwtRequest) {
        try {
            final String username = authJwtRequest.getUserName();
            final String password = authJwtRequest.getPassword();

            final String jwtToken = authApp.authenticateWithUsernameAndPassword(username, password);

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
