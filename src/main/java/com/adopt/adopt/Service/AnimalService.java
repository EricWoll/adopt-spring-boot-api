package com.adopt.adopt.Service;

import com.adopt.adopt.Exception.CustomExceptions.AnimalNotFoundException;
import com.adopt.adopt.Model.Animal;
import com.adopt.adopt.Repo.AnimalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepo animalRepo;

    public List<Animal> findAll() {
        return animalRepo.findAll();
    }

    public Animal findOne(UUID animalId) {
        return animalRepo.findByanimalId(animalId)
                .orElseThrow(()-> new AnimalNotFoundException("Animal Does Not Exist!"));
    }

    public Animal createAnimal(
            String name,
            String type,
            String size,
            String weight,
            List<String> medications,
            boolean hasChip
    ) {
        return animalRepo.insert(
                new Animal(
                        name,
                        type,
                        size,
                        weight,
                        medications,
                        hasChip
                )
        );
    }

    public Animal updateAnimal(
            UUID animalId,
            String name,
            String type,
            String size,
            String weight,
            List<String> medications,
            boolean hasChip
    ) {
        Animal animal = animalRepo.findByanimalId(animalId)
                .orElseThrow(()-> new AnimalNotFoundException("Animal Record Not Found!"));

        animal.setName(name);
        animal.setType(type);
        animal.setSize(size);
        animal.setWeight(weight);
        animal.setMedications(medications);
        animal.setHasChip(hasChip);

        animalRepo.save(animal);
        return animal;
    }

    public Animal deleteAnimal(UUID animalId) {
        Animal animal = animalRepo.findByanimalId(animalId)
                .orElseThrow(()-> new AnimalNotFoundException("Animal Record Not Found!"));

        animalRepo.deleteByanimalId(animalId);

        return animal;
    }
}
