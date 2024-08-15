package com.adopt.adopt.Households;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;

    public List<Household> allHouseholds() {
        return householdRepository.findAll();
    }

    public Household singleHousehold(String householdId) {
        return householdRepository.findHouseholdByhouseholdId(householdId);
    }

    public Household createHousehold(
            String householdId,
            String lastName,
            int peopleAmount,
            List<String> currentPets,
            boolean paperworkComplete
    ) {
        return householdRepository.insert(
            new Household(
                    householdId,
                    lastName,
                    peopleAmount,
                    currentPets,
                    paperworkComplete
            )
        );
    }

    public Household updateHousehold(
            String householdId,
            String lastName,
            int peopleAmount,
            List<String> currentPets,
            boolean paperworkComplete
    ) {
        Household household = householdRepository.findHouseholdByhouseholdId(householdId);

        household.setLastName(lastName);
        household.setPeopleAmount(peopleAmount);
        household.setCurrentPets(currentPets);
        household.setPaperworkComplete(paperworkComplete);

        householdRepository.save(household);

        return household;
    }

    public Household deleteHousehold(String householdId) {
        return householdRepository.deleteHouseholdByhouseholdId(householdId);
    }
}
