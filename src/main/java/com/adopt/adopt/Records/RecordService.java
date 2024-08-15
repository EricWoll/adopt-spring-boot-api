package com.adopt.adopt.Records;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public List<Record> allRecords() {
        return recordRepository.findAll();
    }

    public Record singleRecord(String adoptionId) {
        return recordRepository.findRecordByadoptionId(adoptionId);
    }

    public Record createRecord(
            String adoptionId,
            String animalId,
            String householdId,
            boolean adoptionComplete,
            String adoptionDate
    ) {
        return recordRepository.insert(
                new Record(
                        adoptionId,
                        animalId,
                        householdId,
                        adoptionComplete,
                        adoptionDate
                )
        );
    }

    public Record updateRecord(
            String adoptionId,
            String animalId,
            String householdId,
            boolean adoptionComplete,
            String adoptionDate
    ) {
        Record record = recordRepository.findRecordByadoptionId(adoptionId);

        record.setAnimalId(animalId);
        record.setHouseholdId(householdId);
        record.setAdoptionComplete(adoptionComplete);
        record.setAdoptionDate(adoptionDate);

        recordRepository.save(record);

        return record;
    }

    public Record deleteRecord(String adoptionId) {
        return recordRepository.deleteRecordByadoptionId(adoptionId);
    }
}
