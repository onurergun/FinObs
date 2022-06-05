package com.onurergun.finobsbe.user;

import com.onurergun.finobsbe.common.APIResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "${apiPrefix}" + "users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<APIResponseBody> createUser(@RequestBody User user) {

        User createdUser = userService.createUser(user);

        APIResponseBody responseBody = new APIResponseBody(createdUser.toString());
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }
}
