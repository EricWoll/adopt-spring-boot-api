package com.adopt.adopt.Repo;

import com.adopt.adopt.Model.Animal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepo extends MongoRepository<Animal, String> {

    Optional<Animal> findByanimalId(String animalId);

    boolean existsByanimalId(String animalId);

    void deleteByanimalId(String animalId);
}
