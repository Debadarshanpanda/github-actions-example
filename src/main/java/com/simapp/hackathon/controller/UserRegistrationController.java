package com.simapp.hackathon.controller;

import com.simapp.hackathon.dto.UserRegistrationRequest;
import com.simapp.hackathon.dto.UserRegistrationResponse;
import com.simapp.hackathon.service.UserRegistrationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserRegistrationController {

    @Autowired
    private UserRegistrationService service;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> register(@Valid @RequestBody UserRegistrationRequest request) {
        log.info("Received registration request: {}", request);
        return ResponseEntity.ok(service.registerUser(request));
    }
}

