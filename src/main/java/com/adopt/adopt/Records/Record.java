package com.adopt.adopt.Records;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "adoption_records")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    private ObjectId id;
    private String adoptionId;
    private String animalId;
    private String householdId;
    private boolean adoptionComplete;
    private String adoptionDate;

    public Record(String adoptionId, String animalId, String householdId, boolean adoptionComplete, String adoptionDate) {
        this.adoptionId = adoptionId;
        this.animalId = animalId;
        this.householdId = householdId;
        this.adoptionComplete = adoptionComplete;
        this.adoptionDate = adoptionDate;
    }
}
