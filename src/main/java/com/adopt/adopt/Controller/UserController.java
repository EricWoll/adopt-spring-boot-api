package com.adopt.adopt.Controller;

import com.adopt.adopt.AWS.AwsService;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;


    // When returning an object to the user, make a custom "Model" to send back.
    // This way you can avoid sending info you don't want to go to the user.

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

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable String userId,
            @RequestBody User payload
    ) {
        return new ResponseEntity<User>(
                userService.updateUser(
                        userId,
                        payload
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
