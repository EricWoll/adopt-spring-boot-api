package com.adopt.adopt.Service;

import com.adopt.adopt.Exception.CustomExceptions.UserNotFoundException;
import com.adopt.adopt.Exception.CustomExceptions.UserExistsException;
import com.adopt.adopt.Model.ERole;
import com.adopt.adopt.Model.JwtAuthResponse;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Repo.UserRepo;
import com.adopt.adopt.Security.Jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private BCryptPasswordEncoder BcpEncoder =  new BCryptPasswordEncoder(10);

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findOne(String userId) {
        return userRepo.findByuserId(userId)
                .orElseThrow(()-> new UserNotFoundException("User Does Not Exist!"));
    }

    // Inserts ERole.CUSTOMER
    public User createUser(
            String username,
            String email,
            String password
    ) {

        if (userRepo.existsByusername(username)) {
            throw new UserExistsException("Username Already Exists!");
        }
        if (userRepo.existsByemail(email)) {
            throw new UserExistsException("Email Already Exists!");
        }

        return userRepo.insert(
                new User(
                        username,
                        email,
                        BcpEncoder.encode(password),
                        ERole.CUSTOMER
                )
        );
    }

    public User updateUser(
            String userId,
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

    public User deleteUser(String userId) {
        User user = userRepo.findByuserId(userId)
                .orElseThrow(()-> new UserNotFoundException("User Does Not Exist!"));

        if (user.getRole() == ERole.ADMIN) {
            throw new UserNotFoundException("User Does Not Exist!");
        }

        userRepo.deleteByuserId(userId);
        return user;
    }

    public JwtAuthResponse login(User user) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        String token = jwtService.generateJwtToken(user.getUsername(), user.getPassword());

        return JwtAuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .expiresIn(jwtService.getJwtExpirationDate())
                .build();
    }
}
