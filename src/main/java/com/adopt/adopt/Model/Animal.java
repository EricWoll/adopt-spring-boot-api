package com.adopt.adopt.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Entity(name = "Animal")
@Data
@NoArgsConstructor
@Document(collection = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID animalId;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    private String size;
    private String weight;
    private List<String> medications;
    private boolean hasChip;

    public Animal(
            String name,
            String type,
            String size,
            String weight,
            List<String> medications,
            boolean hasChip
    ) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.weight = weight;
        this.medications = medications;
        this.hasChip = hasChip;
    }
}
