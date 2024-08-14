package com.adopt.adopt.Records;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping
    public ResponseEntity<List<Record>> getAllRecords() {
        return new ResponseEntity<List<Record>>(recordService.allRecords(), HttpStatus.OK);
    }

    @GetMapping("/{adoptionId}")
    public ResponseEntity<Optional<Record>> getSingleRecord(@PathVariable String adoptionId) {
        return new ResponseEntity<Optional<Record>>(recordService.singleRecord(adoptionId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Record> createRecord(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Record>(
                recordService.createRecord(
                        payload.get("adoptionId"),
                        payload.get("animalId"),
                        payload.get("householdId"),
                        Boolean.parseBoolean(payload.get("adoptionComplete")),
                        payload.get("adoptionDate")
                ), HttpStatus.CREATED
        );
    }

}
