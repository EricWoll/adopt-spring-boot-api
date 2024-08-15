package com.adopt.adopt.Households;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HouseholdRepository extends MongoRepository<Household, ObjectId> {

    Household findHouseholdByhouseholdId(String householdId);

    Household deleteHouseholdByhouseholdId(String householdId);
}
