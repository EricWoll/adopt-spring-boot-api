package com.adopt.adopt.Service;

import com.adopt.adopt.Exception.CustomExceptions.AdoptionRecordExistsException;
import com.adopt.adopt.Exception.CustomExceptions.AdoptionRecordNotFoundException;
import com.adopt.adopt.Model.AdoptionRecord;
import com.adopt.adopt.Model.EAdoptionProcess;
import com.adopt.adopt.Repo.AdoptionRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdoptionRecordService {

    @Autowired
    private AdoptionRecordRepo adoptionRecordRepo;

    public List<AdoptionRecord> findAll() {
        return adoptionRecordRepo.findAll();
    }

    public AdoptionRecord findOne(UUID adoptionId) {
        return adoptionRecordRepo.findByadoptionId(adoptionId)
                .orElseThrow(()-> new AdoptionRecordNotFoundException("Adoption Record Does Not Exist!"));
    }

    public AdoptionRecord createAdoptionRecord(
            UUID animalId,
            UUID userId,
            EAdoptionProcess adoptionProcess
    ) {
        Optional<AdoptionRecord> adoptionRecord = adoptionRecordRepo.findByanimalId(animalId);

        if (adoptionRecord.isPresent()) {
            throw new AdoptionRecordExistsException("Adoption Record Already Exists!");
        }

        return adoptionRecordRepo.insert(
                new AdoptionRecord(
                        animalId,
                        userId,
                        adoptionProcess
                )
        );
    }

    public AdoptionRecord updateAdoptionRecord(
            UUID adoptionId,
            UUID animalId,
            UUID userId,
            EAdoptionProcess adoptionProcess
    ) {
        AdoptionRecord adoptionRecord = adoptionRecordRepo.findByadoptionId(adoptionId)
                .orElseThrow(()-> new AdoptionRecordNotFoundException("Adoption Record Does Not Exist!"));

        adoptionRecord.setAnimalId(animalId);
        adoptionRecord.setUserId(userId);
        adoptionRecord.setAdoptionProcess(adoptionProcess);

        adoptionRecordRepo.save(adoptionRecord);
        return adoptionRecord;
    }

    public AdoptionRecord deleteAdoptionRecord(UUID adoptionId) {
        AdoptionRecord adoptionRecord = adoptionRecordRepo.findByadoptionId(adoptionId)
                .orElseThrow(()-> new AdoptionRecordNotFoundException("Adoption Record Does Not Exist!"));

        adoptionRecordRepo.deleteByadoptionId(adoptionId);
        return adoptionRecord;
    }
}
