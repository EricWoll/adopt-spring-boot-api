package com.adopt.adopt.Animals;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> allAnimals() {
        return animalRepository.findAll();
    }

    public Optional<Animal> singleAnimal(String animalId) {
        return animalRepository.findAnimalByanimalId(animalId);
    }
}
