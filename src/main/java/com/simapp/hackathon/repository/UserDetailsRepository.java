package com.simapp.hackathon.repository;

import com.simapp.hackathon.entity.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {
    boolean existsByAadhaarNumber(String aadhaarNumber);
    boolean existsByEmailId(String emailId);
}

