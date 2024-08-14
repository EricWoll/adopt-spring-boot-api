package com.adopt.adopt.Households;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "households")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Household {
    @Id
    private ObjectId id;
    private String householdId;
    private String lastName;
    private int peopleAmount;
    private List<String> currentPets;
    private boolean paperworkComplete;
}
