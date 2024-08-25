package com.adopt.adopt.Controller;

import com.adopt.adopt.Model.User;
import com.adopt.adopt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(
                userService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findSingleUser(@PathVariable String userId) {
        return new ResponseEntity<User>(
                userService.findOne(userId),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User payload) {
        return new ResponseEntity<User>(
                userService.createUser(
                        payload.getUsername(),
                        payload.getEmail(),
                        payload.getPassword()
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable String userId,
            @RequestBody User payload
    ) {
        return new ResponseEntity<User>(
                userService.updateUser(
                        userId,
                        payload.getUsername(),
                        payload.getEmail(),
                        payload.getPassword()
                ),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId) {
        return new ResponseEntity<User>(
                userService.deleteUser(userId),
                HttpStatus.NO_CONTENT
        );
    }
}
