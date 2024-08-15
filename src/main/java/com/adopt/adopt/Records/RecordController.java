package com.adopt.adopt.Records;

import com.adopt.adopt.Exceptions.ApiDeleteException;
import com.adopt.adopt.Exceptions.ApiGetException;
import com.adopt.adopt.Exceptions.ApiPostException;
import com.adopt.adopt.Exceptions.ApiPutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public ResponseEntity<List<Record>> getAllRecords() {
        return new ResponseEntity<List<Record>>(recordService.allRecords(), HttpStatus.OK);
    }

    @GetMapping("/{adoptionId}")
    public ResponseEntity<Record> getSingleRecord(@PathVariable String adoptionId) {

        if (!mongoTemplate.exists(
                Query.query(Criteria.where("adoptionId").is(adoptionId)),Record.class))
        {
            throw new ApiGetException("Adoption Record Does Not Exists!");
        }

        return new ResponseEntity<Record>(recordService.singleRecord(adoptionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Record> createRecord(@RequestBody Record payload) {

        if (mongoTemplate.exists(
                Query.query(Criteria.where("adoptionId").is(payload.getAdoptionId())),Record.class))
        {
            throw new ApiPostException("Adoption Record Already Exists!");
        }

        return new ResponseEntity<Record>(recordService.createRecord(
                payload.getAdoptionId(),
                payload.getAnimalId(),
                payload.getHouseholdId(),
                payload.isAdoptionComplete(),
                payload.getAdoptionDate()
        ), HttpStatus.CREATED);
    }

    @PutMapping("/{adoptionId}")
    public ResponseEntity<Record> updateRecord(
            @PathVariable String adoptionId,
            @RequestBody Record payload
    ) {

        if (!mongoTemplate.exists(
                Query.query(Criteria.where("adoptionId").is(adoptionId)), Record.class))
        {
            throw new ApiPutException("Adoption Record Already Exists!");
        }

        return new ResponseEntity<Record>(recordService.updateRecord(
                adoptionId,
                payload.getAnimalId(),
                payload.getHouseholdId(),
                payload.isAdoptionComplete(),
                payload.getAdoptionDate()
        ), HttpStatus.CREATED);

    }

    @DeleteMapping("/{adoptionId}")
    public ResponseEntity<Record> deleteRecord(@PathVariable String adoptionId) {

        if (!mongoTemplate.exists(
                Query.query(Criteria.where("adoptionId").is(adoptionId)), Record.class))
        {
            throw new ApiDeleteException("Adoption Record Already Exists!");
        }

        return new ResponseEntity<Record>(recordService.deleteRecord(adoptionId), HttpStatus.NO_CONTENT);
    }

}
