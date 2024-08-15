package com.adopt.adopt.Households;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "households")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Household {
    @MongoId
    private ObjectId id;
    private String householdId;
    private String lastName;
    private int peopleAmount;
    private List<String> currentPets;
    private boolean paperworkComplete;

    public Household(
            String householdId,
            String lastName,
            int peopleAmount,
            List<String> currentPets,
            boolean paperworkComplete
    ) {
        this.householdId = householdId;
        this.lastName = lastName;
        this.peopleAmount = peopleAmount;
        this.currentPets = currentPets;
        this.paperworkComplete = paperworkComplete;
    }
}
