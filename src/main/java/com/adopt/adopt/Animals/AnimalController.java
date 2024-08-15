package com.adopt.adopt.Animals;

import com.adopt.adopt.Exceptions.ApiGetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimals() {
        return new ResponseEntity<List<Animal>>(animalService.allAnimals(), HttpStatus.OK);
    }

    @GetMapping("/{animalId}")
    public ResponseEntity<Animal> getSingleAnimal(@PathVariable String animalId) {

        if (!mongoTemplate.exists(
                Query.query(Criteria.where("animalId").is(animalId)), Animal.class))
        {
            throw new ApiGetException("Animal Does not Exist!");
        }

        return new ResponseEntity<Animal>(animalService.singleAnimal((animalId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Animal> createAnimal(@RequestBody Animal payload) {

        if (mongoTemplate.exists(
                Query.query(Criteria.where("animalId").is(payload.getAnimalId())), Animal.class))
        {
            throw new ApiGetException("Animal Already Exists!");
        }

        return new ResponseEntity<Animal>(animalService.createAnimal(
                payload.getAnimalId(),
                payload.getName(),
                payload.getType(),
                payload.getSize(),
                payload.getWeight(),
                payload.isHasChip(),
                payload.getMedications()
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{animalId}")
    public ResponseEntity<Animal> updateAnimal(
            @PathVariable String animalId,
            @RequestBody Animal payload
    ) {
        if (!mongoTemplate.exists(
                Query.query(Criteria.where("animalId").is(animalId)), Animal.class))
        {
            throw new ApiGetException("Animal Does not Exist!");
        }

        System.out.println(payload);

        return new ResponseEntity<Animal>(animalService.updateAnimal(
            animalId,
            payload.getName(),
            payload.getType(),
            payload.getSize(),
            payload.getWeight(),
            payload.isHasChip(),
            payload.getMedications()
        ), HttpStatus.CREATED);
    }

    @DeleteMapping("/{animalId}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable String animalId) {
        if (!mongoTemplate.exists(
                Query.query(Criteria.where("animalId").is(animalId)), Animal.class))
        {
            throw new ApiGetException("Animal Does not Exist!");
        }

        return new ResponseEntity<Animal>(animalService.deleteAnimal(animalId), HttpStatus.NO_CONTENT);
    }
}
