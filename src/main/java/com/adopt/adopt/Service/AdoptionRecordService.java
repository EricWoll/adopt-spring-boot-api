package com.adopt.adopt.Service;

import com.adopt.adopt.Exception.CustomExceptions.AdoptionRecordExistsException;
import com.adopt.adopt.Exception.CustomExceptions.AdoptionRecordNotFoundException;
import com.adopt.adopt.Exception.CustomExceptions.AnimalNotFoundException;
import com.adopt.adopt.Exception.CustomExceptions.UserNotFoundException;
import com.adopt.adopt.Model.AdoptionRecord;
import com.adopt.adopt.Model.Animal;
import com.adopt.adopt.Model.EAdoptionProcess;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Repo.AdoptionRecordRepo;
import com.adopt.adopt.Repo.AnimalRepo;
import com.adopt.adopt.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdoptionRecordService {

    @Autowired
    private AdoptionRecordRepo adoptionRecordRepo;
    @Autowired
    private AnimalRepo animalRepo;
    @Autowired
    private UserRepo userRepo;

    public List<AdoptionRecord> findAll() {
        return adoptionRecordRepo.findAll();
    }

    public AdoptionRecord findOne(String adoptionId) {
        return adoptionRecordRepo.findByadoptionId(adoptionId)
                .orElseThrow(()-> new AdoptionRecordNotFoundException("Adoption Record Does Not Exist!"));
    }

    public AdoptionRecord createAdoptionRecord(
            String animalId,
            String userId,
            EAdoptionProcess adoptionProcess
    ) {
        if (!animalRepo.existsByanimalId(animalId)) {
            throw new AnimalNotFoundException("Animal Record Not Found!");
        }
         if (!userRepo.existsByuserId(userId)) {
                throw new UserNotFoundException("User Record Not Found!");
         }

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
            String adoptionId,
            String animalId,
            String userId,
            EAdoptionProcess adoptionProcess
    ) {

        animalRepo.findByanimalId(animalId)
                .orElseThrow(()-> new AnimalNotFoundException("Animal Record Not Found!"));

        userRepo.findByuserId(userId)
                .orElseThrow(()-> new UserNotFoundException("User Record Not Found!"));

        AdoptionRecord adoptionRecord = adoptionRecordRepo.findByadoptionId(adoptionId)
                .orElseThrow(()-> new AdoptionRecordNotFoundException("Adoption Record Does Not Exist!"));

        adoptionRecord.setAnimalId(animalId);
        adoptionRecord.setUserId(userId);
        adoptionRecord.setAdoptionProcess(adoptionProcess);

        adoptionRecordRepo.save(adoptionRecord);
        return adoptionRecord;
    }

    public AdoptionRecord deleteAdoptionRecord(String adoptionId) {
        AdoptionRecord adoptionRecord = adoptionRecordRepo.findByadoptionId(adoptionId)
                .orElseThrow(()-> new AdoptionRecordNotFoundException("Adoption Record Does Not Exist!"));

        adoptionRecordRepo.deleteByadoptionId(adoptionId);
        return adoptionRecord;
    }
}
