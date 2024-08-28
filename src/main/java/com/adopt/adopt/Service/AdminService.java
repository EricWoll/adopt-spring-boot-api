package com.adopt.adopt.Service;

import com.adopt.adopt.Exception.CustomExceptions.AdoptionRecordNotFoundException;
import com.adopt.adopt.Exception.CustomExceptions.AnimalNotFoundException;
import com.adopt.adopt.Exception.CustomExceptions.UserExistsException;
import com.adopt.adopt.Exception.CustomExceptions.UserNotFoundException;
import com.adopt.adopt.Model.AdoptionRecord;
import com.adopt.adopt.Model.EAdoptionProcess;
import com.adopt.adopt.Model.ERole;
import com.adopt.adopt.Model.User;
import com.adopt.adopt.Repo.AdoptionRecordRepo;
import com.adopt.adopt.Repo.AnimalRepo;
import com.adopt.adopt.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AdoptionRecordRepo adoptionRecordRepo;
    @Autowired
    private AnimalRepo animalRepo;

    private BCryptPasswordEncoder BcpEncoder =  new BCryptPasswordEncoder(10);

    // Creates For All User Types
    public User createUser(User user) {
        boolean foundName = userRepo.existsByusername(user.getUsername());
        boolean foundEmail = userRepo.existsByemail(user.getEmail());

        if (foundName || foundEmail) {
            throw new UserExistsException("User Already Exists!");
        }

        return userRepo.insert(
                new User(
                        user.getUsername(),
                        user.getEmail(),
                        BcpEncoder.encode(user.getPassword()),
                        user.getRole()
                )
        );
    }

    // Updates For All User Types
    public User updateUser(User user) {
        User foundUser = userRepo.findByuserId(user.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User Does Not Exist!"));

        foundUser.setUsername(user.getUsername());
        foundUser.setEmail(user.getEmail());
        foundUser.setPassword(BcpEncoder.encode(user.getPassword()));
        foundUser.setRole(user.getRole());

        userRepo.save(foundUser);
        return foundUser;
    }

    // Deletes For All User Types
    public User deleteUser(String userId) {
        User user = userRepo.findByuserId(userId)
                .orElseThrow(()-> new UserNotFoundException("User Does Not Exist!"));

        userRepo.deleteByuserId(userId);
        return user;
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
}
