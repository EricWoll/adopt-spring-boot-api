package com.adopt.adopt.Repo;

import com.adopt.adopt.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findByusername(String username);
    Optional<User> findByuserId(String userId);
    Optional<User> findByemail(String email);

    boolean existsByusername(String username);
    boolean existsByemail(String email);
    boolean existsByuserId(String userId);

    void deleteByuserId(String userId);
}