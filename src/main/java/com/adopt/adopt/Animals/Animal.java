package com.adopt.adopt.Animals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    @Id
    private ObjectId id;
    private String animalId;
    private String name;
    private String type;
    private String size;
    private String weight;
    private boolean hasChip;
    private List<String> medications;
}
