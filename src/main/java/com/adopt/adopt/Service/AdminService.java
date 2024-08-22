package com.adopt.adopt.Service;

import com.adopt.adopt.Exception.CustomExceptions.UserExistsException;
import com.adopt.adopt.Exception.CustomExceptions.UserNotFoundException;
import com.adopt.adopt.Model.ERole;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;

    // Creates For All User Types
    public User createUser(
            String username,
            String email,
            String password,
            ERole role
    ) {
        boolean foundName = userRepo.existsByusername(username);
        boolean foundEmail = userRepo.existsByemail(email);

        if (foundName || foundEmail) {
            throw new UserExistsException("User Already Exists!");
        }

        return userRepo.insert(
                new User(
                        username,
                        email,
                        password,
                        role
                )
        );
    }

    // Updates For All User Types
    public User updateUser(
            UUID userId,
            String username,
            String email,
            String password,
            ERole role
    ) {
        User user = userRepo.findByuserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User Does Not Exist!"));

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        userRepo.save(user);
        return user;
    }

    // Deletes For All User Types
    public User deleteUser(UUID userId) {
        User user = userRepo.findByuserId(userId)
                .orElseThrow(()-> new UserNotFoundException("User Does Not Exist!"));

        userRepo.deleteByuserId(userId);
        return user;
    }
}
