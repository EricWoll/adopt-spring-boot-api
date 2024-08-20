package com.adopt.adopt.Model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity(name = "Adoption Record")
@Data
@NoArgsConstructor
@Document(collection = "adoption_records")
public class AdoptionRecord {

}
