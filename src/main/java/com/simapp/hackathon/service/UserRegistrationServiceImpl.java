package com.simapp.hackathon.service;

import com.simapp.hackathon.dto.UserRegistrationRequest;
import com.simapp.hackathon.dto.UserRegistrationResponse;
import com.simapp.hackathon.entity.UserDetails;
import com.simapp.hackathon.exception.DuplicateUserException;
import com.simapp.hackathon.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private UserDetailsRepository repository;

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {
        log.info("Registering user with Aadhaar: {}", request.getAadhaarNumber());
        if (repository.existsByAadhaarNumber(request.getAadhaarNumber()) || repository.existsByEmailId(request.getEmailId())) {
            throw new DuplicateUserException("User already exists with either given Aadhaar" +request.getAadhaarNumber()+" or Email "+request.getEmailId());
        }
        Long mobileNumber = generateMobileNumber();
        UserDetails user = new UserDetails(null, request.getUserName(), request.getAadhaarNumber(),
                request.getAddress(), request.getEmailId(), mobileNumber);
        repository.save(user);
        log.info("User registered successfully with phone number: {}", mobileNumber);
        return new UserRegistrationResponse("user registered successfully", mobileNumber);
    }
    private Long generateMobileNumber() {
        return 9000000000L + new Random().nextInt(100000000);
    }
}

