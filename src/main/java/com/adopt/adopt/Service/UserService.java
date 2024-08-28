package com.adopt.adopt.Service;

import com.adopt.adopt.Exception.CustomExceptions.UserNotFoundException;
import com.adopt.adopt.Exception.CustomExceptions.UserExistsException;
import com.adopt.adopt.Model.ERole;
import com.adopt.adopt.Model.AuthResponse;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Repo.UserRepo;
import com.adopt.adopt.Security.Jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public AuthResponse createUser(User user) {

        if (userRepo.existsByusername(user.getUsername())) {
            throw new UserExistsException("Username Already Exists!");
        }
        if (userRepo.existsByemail(user.getEmail())) {
            throw new UserExistsException("Email Already Exists!");
        }

        User createdUser = userRepo.insert(
            new User(
                user.getUsername(),
                user.getEmail(),
                BcpEncoder.encode(user.getPassword()),
                ERole.CUSTOMER
            )
        );

        return JwtToken(createdUser);
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
        user.setPassword(BcpEncoder.encode(password));

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

    public AuthResponse login(User user) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
            )
        );

        User foundUser = userRepo.findByusername(user.getUsername())
                .orElseThrow(()-> new UserNotFoundException("User Was not Found!"));

        return JwtToken(foundUser);

    }

    private AuthResponse JwtToken(User user) {
        String accessToken = jwtService.generateJwtToken(user);
        String refreshToken = jwtService.generateJwtRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getUserId())
                .build();
    }

    public AuthResponse refreshJwtToken(
            HttpServletRequest request
    ) {
        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            String refreshToken = authHeader.substring(7);

            if (StringUtils.hasText(refreshToken) && jwtService.validateToken(refreshToken)) {
                String username = jwtService.getUsername(refreshToken);
                User user = userRepo.findByusername(username)
                        .orElseThrow(()-> new UsernameNotFoundException("Username Does Not Exist!"));

                String accessToken = jwtService.generateJwtToken(user);

                 return AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                         .userId(user.getUserId())
                        .build();
            }
        }
        return null;
    }
}
