package com.adopt.adopt.Animals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> allAnimals() {
        return animalRepository.findAll();
    }

    public Animal singleAnimal(String animalId) {
        return animalRepository.findAnimalByanimalId(animalId);
    }

    public Animal createAnimal(
            String animalId,
            String name,
            String type,
            String size,
            String weight,
            boolean hasChip,
            List<String> medications
    ) {
        return animalRepository.insert(
                new Animal(
                        animalId,
                        name,
                        type,
                        size,
                        weight,
                        hasChip,
                        medications
                )
        );
    }

    public Animal updateAnimal(
            String animalId,
            String name,
            String type,
            String size,
            String weight,
            boolean hasChip,
            List<String> medications
    ) {
        Animal animal = animalRepository.findAnimalByanimalId(animalId);

        animal.setName(name);
        animal.setType(type);
        animal.setSize(size);
        animal.setWeight(weight);
        animal.setHasChip(hasChip);
        animal.setMedications(medications);

        animalRepository.save(animal);

        return animal;
    }

    public Animal deleteAnimal(String animalId) {
        return animalRepository.deleteAnimalByanimalId(animalId);
    }
}
