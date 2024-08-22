package com.adopt.adopt.Repo;

import com.adopt.adopt.Model.AdoptionRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdoptionRecordRepo extends MongoRepository<AdoptionRecord, String> {

    Optional<AdoptionRecord> findByadoptionId(UUID adoptionId);
    Optional<AdoptionRecord> findByanimalId(UUID animalId);

    boolean existsByadoptionId(UUID adoptionId);
    boolean existsByanimalId(UUID animalId);

    void deleteByadoptionId(UUID adoptionId);
}
