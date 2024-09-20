package com.adopt.adopt.Model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "animals")
public class Animal {
    @MongoId
    private ObjectId id;
    private String animalId;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    private String size;
    private String weight;
    private List<String> medications;
    private boolean hasChip;
    private String imageId;

    public Animal(
            String name,
            String type,
            String size,
            String weight,
            List<String> medications,
            boolean hasChip,
            String imageId
    ) {
        this.animalId = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.size = size;
        this.weight = weight;
        this.medications = medications;
        this.hasChip = hasChip;
        this.imageId = imageId;
    }
}
