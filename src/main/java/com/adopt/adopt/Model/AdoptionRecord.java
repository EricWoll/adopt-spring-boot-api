package com.adopt.adopt.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Entity(name = "Adoption Record")
@Data
@NoArgsConstructor
@Document(collection = "adoption_records")
public class AdoptionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID adoptionId;
    private UUID animalId;
    private UUID userId;
    private EAdoptionProcess adoptionProcess;

    public AdoptionRecord(
            UUID animalId,
            UUID userId,
            EAdoptionProcess adoptionProcess
    ) {
        this.animalId = animalId;
        this.userId = userId;
        this.adoptionProcess = adoptionProcess;
    }
}
