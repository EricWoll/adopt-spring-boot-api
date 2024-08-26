package com.adopt.adopt.Controller;

import com.adopt.adopt.Model.JwtAuthResponse;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User payload) {
        return new ResponseEntity<User>(
                userService.createUser(
                        payload.getUsername(),
                        payload.getEmail(),
                        payload.getPassword()
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }
}
