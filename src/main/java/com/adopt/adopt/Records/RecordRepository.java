package com.adopt.adopt.Records;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends MongoRepository<Record, ObjectId> {

    Record findRecordByadoptionId(String adoptionId);

    Record deleteRecordByadoptionId(String adoptionId);

}
