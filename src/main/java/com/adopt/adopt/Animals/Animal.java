package com.adopt.adopt.Animals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @MongoId
    private ObjectId id;
    private String animalId;
    private String name;
    private String type;
    private String size;
    private String weight;
    private boolean hasChip;
    private List<String> medications;


    public Animal (
            String animalId,
            String name,
            String type,
            String size,
            String weight,
            boolean hasChip,
            List<String> medications
    ){
        this.animalId = animalId;
        this.name = name;
        this.type = type;
        this.size = size;
        this.weight = weight;
        this.hasChip = hasChip;
        this.medications = medications;
    }
}
