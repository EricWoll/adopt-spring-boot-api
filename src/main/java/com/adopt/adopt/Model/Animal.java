package com.adopt.adopt.Model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity(name = "Animal")
@Data
@NoArgsConstructor
@Document(collection = "animals")
public class Animal {
}
