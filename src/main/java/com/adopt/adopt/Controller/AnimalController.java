package com.adopt.adopt.Controller;

import com.adopt.adopt.Model.Animal;
import com.adopt.adopt.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
public class AnimalController {
    @Autowired
    private AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return new ResponseEntity<List<Animal>>(
                animalService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<Animal> findSingleAnimal(@PathVariable String animalId) {
        return new ResponseEntity<Animal>(
                animalService.findOne(animalId),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal payload) {
        return new ResponseEntity<Animal>(
                animalService.createAnimal(
                        payload
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<Animal> updateAnimal(
            @PathVariable String animalId,
            @RequestBody Animal payload
    ) {
        return new ResponseEntity<Animal>(
                animalService.updateAnimal(
                        animalId,
                        payload
                ),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable String animalId) {
        return new ResponseEntity<Animal>(
                animalService.deleteAnimal(animalId),
                HttpStatus.NO_CONTENT
        );
    }
}
