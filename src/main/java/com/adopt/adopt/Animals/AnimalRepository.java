package com.adopt.adopt.Animals;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends MongoRepository<Animal, ObjectId> {

    Animal findAnimalByanimalId(String animalId);

    Animal deleteAnimalByanimalId(String animalId);
}
