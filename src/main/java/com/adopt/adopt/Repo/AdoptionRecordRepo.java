package com.adopt.adopt.Repo;

import com.adopt.adopt.Model.AdoptionRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdoptionRecordRepo extends MongoRepository<AdoptionRecord, String> {

    Optional<AdoptionRecord> findByadoptionId(String adoptionId);
    Optional<AdoptionRecord> findByanimalId(String animalId);

    boolean existsByadoptionId(String adoptionId);
    boolean existsByanimalId(String animalId);

    void deleteByadoptionId(String adoptionId);
}
