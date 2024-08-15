package com.adopt.adopt.Households;

import com.adopt.adopt.Exceptions.ApiGetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/households")
public class HouseholdController {

    @Autowired
    private HouseholdService householdService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public ResponseEntity<List<Household>> getAllHouseholds() {
        return new ResponseEntity<List<Household>>(householdService.allHouseholds(), HttpStatus.OK);
    }

    @GetMapping("/{householdId}")
    public ResponseEntity<Household> getSingleHousehold(@PathVariable String householdId) {

        if (!mongoTemplate.exists(
                Query.query(Criteria.where("householdId").is(householdId)), Household.class))
        {
            throw new ApiGetException("Household Does Not Exist!");
        }

        return new ResponseEntity<Household>(householdService.singleHousehold(householdId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Household> createHousehold(@RequestBody Household payload) {

        if (mongoTemplate.exists(
                Query.query(Criteria.where("householdId").is(payload.getHouseholdId())), Household.class))
        {
            throw new ApiGetException("Household Already Exists!");
        }

        return new ResponseEntity<Household>(
                householdService.createHousehold(
                    payload.getHouseholdId(),
                    payload.getLastName(),
                    payload.getPeopleAmount(),
                    payload.getCurrentPets(),
                    payload.isPaperworkComplete()
                ),
                HttpStatus.CREATED);
    }

    @PutMapping("/{householdId}")
    public ResponseEntity<Household> updateHousehold(
            @PathVariable String householdId,
            @RequestBody Household payload
            ) {
        if (!mongoTemplate.exists(
                Query.query(Criteria.where("householdId").is(householdId)), Household.class))
        {
            throw new ApiGetException("Household Does Not Exist!");
        }

        return new ResponseEntity<Household>(
                householdService.updateHousehold(
                        householdId,
                        payload.getLastName(),
                        payload.getPeopleAmount(),
                        payload.getCurrentPets(),
                        payload.isPaperworkComplete()
                ), HttpStatus.CREATED);
    }

    @DeleteMapping("/{householdId}")
    public ResponseEntity<Household> deleteHousehold(@PathVariable String householdId) {

        if (!mongoTemplate.exists(
                Query.query(Criteria.where("householdId").is(householdId)), Household.class))
        {
            throw new ApiGetException("Household Does Not Exist!");
        }

        return new ResponseEntity<Household>(
                householdService.deleteHousehold(householdId),
                HttpStatus.NO_CONTENT
        );
    }
}
