package com.adopt.adopt.Controller;

import com.adopt.adopt.Model.AdoptionRecord;
import com.adopt.adopt.Service.AdoptionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/adoption-records")
public class AdoptionRecordController {

    @Autowired
    private AdoptionRecordService adoptionRecordService;

    @GetMapping
    public ResponseEntity<List<AdoptionRecord>> getAllAdoptionRecords() {
        return new ResponseEntity<List<AdoptionRecord>>(
                adoptionRecordService.findAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{adoptionId}")
    public ResponseEntity<AdoptionRecord> getSingleAdoptionRecordById(@PathVariable String adoptionId) {
        return new ResponseEntity<AdoptionRecord>(
                adoptionRecordService.findOneById(adoptionId),
                HttpStatus.OK
        );
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<AdoptionRecord>> getAllAdoptionRecordsByUser(@PathVariable String userId) {
        return new ResponseEntity<List<AdoptionRecord>>(
                adoptionRecordService.findByUser(userId),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<AdoptionRecord> createAdoptionRecord(@RequestBody AdoptionRecord payload) {
        return new ResponseEntity<AdoptionRecord>(
                adoptionRecordService.createAdoptionRecord(
                        payload.getAnimalId(),
                        payload.getUserId()
                ),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{adoptionId}")
    public ResponseEntity<AdoptionRecord> updateAdoptionRecord(
            @PathVariable String adoptionId,
            @RequestBody AdoptionRecord payload
    ) {
        return new ResponseEntity<AdoptionRecord>(
                adoptionRecordService.updateAdoptionRecord(
                        adoptionId,
                        payload.getAnimalId(),
                        payload.getUserId()
                ),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{adoptionId}")
    public ResponseEntity<AdoptionRecord> deleteAdoptionRecord(@PathVariable String adoptionId) {
        return new ResponseEntity<AdoptionRecord>(
                adoptionRecordService.deleteAdoptionRecord(adoptionId),
                HttpStatus.NO_CONTENT
        );
    }
}
