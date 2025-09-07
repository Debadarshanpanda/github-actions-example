package com.simapp.hackathon.service;

import com.simapp.hackathon.dto.UserRegistrationRequest;
import com.simapp.hackathon.dto.UserRegistrationResponse;

public interface UserRegistrationService {
    UserRegistrationResponse registerUser(UserRegistrationRequest request);
}

