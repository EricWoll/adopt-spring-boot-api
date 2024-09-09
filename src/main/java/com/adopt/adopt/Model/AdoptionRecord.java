package com.adopt.adopt.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = "adoption_records")
public class AdoptionRecord {

    @MongoId
    private ObjectId id;
    private String adoptionId;
    private String animalId;
    private String userId;
    private EAdoptionProcess adoptionProcess;

    public AdoptionRecord(
            String animalId,
            String userId,
            EAdoptionProcess adoptionProcess
    ) {
        this.adoptionId = UUID.randomUUID().toString();
        this.animalId = animalId;
        this.userId = userId;
        this.adoptionProcess = adoptionProcess;
    }
}
