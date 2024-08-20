package com.adopt.adopt.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Entity(name = "Household")
@Data
@NoArgsConstructor
@Document(collection = "households")
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID householdId;
    // @ForeignKey
    private UUID userId;
    private List<String> currentPets;

}
