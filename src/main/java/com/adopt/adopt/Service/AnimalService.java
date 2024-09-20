package com.adopt.adopt.Service;

import com.adopt.adopt.Exception.CustomExceptions.AnimalNotFoundException;
import com.adopt.adopt.Model.Animal;
import com.adopt.adopt.Repo.AnimalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepo animalRepo;

    @Value("adopt.app.noImage")
    private String noImageId;

    public List<Animal> findAll() {
        return animalRepo.findAll();
    }

    public Animal findOne(String animalId) {
        return animalRepo.findByanimalId(animalId)
                .orElseThrow(()-> new AnimalNotFoundException("Animal Does Not Exist!"));
    }

    public Animal createAnimal(Animal animal
    ) {
        if (animal.getImageId() == null) {
            animal.setImageId(noImageId);
        }

        return animalRepo.insert(
                new Animal(
                        animal.getName(),
                        animal.getType(),
                        animal.getSize(),
                        animal.getWeight(),
                        animal.getMedications(),
                        animal.isHasChip(),
                        animal.getImageId()
                )
        );
    }

    public Animal updateAnimal(String animalId, Animal animal
    ) {
        Animal foundAnimal = animalRepo.findByanimalId(animalId)
                .orElseThrow(()-> new AnimalNotFoundException("Animal Record Not Found!"));

        foundAnimal.setName(animal.getName());
        foundAnimal.setType(animal.getSize());
        foundAnimal.setSize(animal.getSize());
        foundAnimal.setWeight(animal.getWeight());
        foundAnimal.setMedications(animal.getMedications());
        foundAnimal.setHasChip(animal.isHasChip());
        foundAnimal.setImageId(animal.getImageId());

        animalRepo.save(foundAnimal);
        return foundAnimal;
    }

    public Animal deleteAnimal(String animalId) {
        Animal animal = animalRepo.findByanimalId(animalId)
                .orElseThrow(()-> new AnimalNotFoundException("Animal Record Not Found!"));

        animalRepo.deleteByanimalId(animalId);

        return animal;
    }
}
