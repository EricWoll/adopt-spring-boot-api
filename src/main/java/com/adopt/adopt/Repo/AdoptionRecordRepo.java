package com.adopt.adopt.Repo;

import com.adopt.adopt.Model.AdoptionRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptionRecordRepo extends MongoRepository<AdoptionRecord, String> {


}
