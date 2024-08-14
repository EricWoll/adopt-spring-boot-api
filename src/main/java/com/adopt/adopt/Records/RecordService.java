package com.adopt.adopt.Records;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public List<Record> allRecords() {
        return recordRepository.findAll();
    }

    public Optional<Record> singleRecord(String adoptionId) {
        return recordRepository.findRecordByadoptionId(adoptionId);
    }

    public Record createRecord(
            String adoptionId,
            String animalId,
            String householdId,
            boolean adoptionComplete,
            String adoptionDate
    ) {

        return recordRepository.insert(new Record(adoptionId, animalId, householdId, adoptionComplete, adoptionDate));
    }
}
