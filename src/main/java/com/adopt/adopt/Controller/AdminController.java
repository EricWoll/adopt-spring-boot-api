package com.adopt.adopt.Controller;

import com.adopt.adopt.Model.AdoptionRecord;
import com.adopt.adopt.Model.Animal;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(adminService.createUser(user));
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable String userId,
            @RequestBody User user
    ) {
        return new ResponseEntity<User>(
                adminService.updateUser(user),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId) {
        return new ResponseEntity<User>(
                adminService.deleteUser(userId),
                HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/adoption-records/{adoptionId}")
    public ResponseEntity<AdoptionRecord> updateAdoptionRecord(
            @PathVariable String adoptionId,
            @RequestBody AdoptionRecord payload
    ) {
        return new ResponseEntity<AdoptionRecord>(
                adminService.updateAdoptionRecord(
                        adoptionId,
                        payload.getAnimalId(),
                        payload.getUserId(),
                        payload.getAdoptionProcess()
                ),
                HttpStatus.CREATED
        );
    }
}
