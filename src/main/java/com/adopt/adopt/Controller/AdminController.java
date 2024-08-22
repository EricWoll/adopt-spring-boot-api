package com.adopt.adopt.Controller;

import com.adopt.adopt.Model.User;
import com.adopt.adopt.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User payload) {
        return new ResponseEntity<User>(
                adminService.createUser(
                        payload.getUsername(),
                        payload.getEmail(),
                        payload.getPassword(),
                        payload.getRole()
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable UUID userId,
            @RequestBody User payload
    ) {
        return new ResponseEntity<User>(
                adminService.updateUser(
                        userId,
                        payload.getUsername(),
                        payload.getEmail(),
                        payload.getPassword(),
                        payload.getRole()
                ),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable UUID userId) {
        return new ResponseEntity<User>(
                adminService.deleteUser(userId),
                HttpStatus.NO_CONTENT
        );
    }
}
