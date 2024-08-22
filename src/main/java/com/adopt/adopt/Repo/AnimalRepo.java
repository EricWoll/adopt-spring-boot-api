package com.adopt.adopt.Repo;

import com.adopt.adopt.Model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnimalRepo extends MongoRepository<Animal, String> {

    Optional<Animal> findByanimalId(UUID animalId);

    boolean existsByanimalId(UUID animalId);

    void deleteByanimalId(UUID animalId);
}
