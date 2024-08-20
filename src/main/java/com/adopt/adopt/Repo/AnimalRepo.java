package com.adopt.adopt.Repo;

import com.adopt.adopt.Model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepo extends MongoRepository<Animal, String> {
}
