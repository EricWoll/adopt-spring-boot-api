package com.adopt.adopt.Repo;

import com.adopt.adopt.Model.AdoptionRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdoptionRecordRepo extends MongoRepository<AdoptionRecord, String> {

    Optional<AdoptionRecord> findByadoptionId(String adoptionId);
    Optional<AdoptionRecord> findByanimalId(String animalId);
    Optional<AdoptionRecord> findByuserId(String userId);

    List<AdoptionRecord> findAllByuserId(String userId);

    boolean existsByadoptionId(String adoptionId);
    boolean existsByanimalId(String animalId);

    void deleteByadoptionId(String adoptionId);
}
