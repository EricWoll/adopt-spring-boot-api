package com.adopt.adopt.Service;

import com.adopt.adopt.Exception.CustomExceptions.UserNotFoundException;
import com.adopt.adopt.Exception.CustomExceptions.UserExistsException;
import com.adopt.adopt.Model.ERole;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findOne(UUID userId) {
        return userRepo.findByuserId(userId)
                .orElseThrow(()-> new UserNotFoundException("User Does Not Exist!"));
    }

    // Inserts ERole.CUSTOMER
    public User createUser(
            String username,
            String email,
            String password
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
                        ERole.CUSTOMER
                )
        );
    }

    public User updateUser(
            UUID userId,
            String username,
            String email,
            String password
    ) {
        User user = userRepo.findByuserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User Does Not Exist!"));

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        userRepo.save(user);
        return user;
    }

    public User deleteUser(UUID userId) {
        User user = userRepo.findByuserId(userId)
                .orElseThrow(()-> new UserNotFoundException("User Does Not Exist!"));

        if (user.getRole() == ERole.ADMIN) {
            throw new UserNotFoundException("User Does Not Exist!");
        }

        userRepo.deleteByuserId(userId);
        return user;
    }
}
