package com.adopt.adopt.Controller;

import com.adopt.adopt.Model.AuthResponse;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<AuthResponse> registerUser(@RequestBody User user) {
        return  ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshUser(
            HttpServletRequest request
    ) {
        return ResponseEntity.ok(userService.refreshJwtToken(request));
    }
}
