package com.simapp.hackathon.serviceImpl;

import com.simapp.hackathon.dto.UserRegistrationRequest;
import com.simapp.hackathon.dto.UserRegistrationResponse;
import com.simapp.hackathon.entity.UserDetails;
import com.simapp.hackathon.exception.DuplicateUserException;
import com.simapp.hackathon.repository.UserDetailsRepository;
import com.simapp.hackathon.service.UserRegistrationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRegistrationServiceImplTest {

    @Mock
    private UserDetailsRepository repository;

    @InjectMocks
    private UserRegistrationServiceImpl service;

    public UserRegistrationServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        UserRegistrationRequest request = new UserRegistrationRequest("John", "123456789012", "Hyderabad", "john@example.com");

        when(repository.existsByAadhaarNumber("123456789012")).thenReturn(false);
        when(repository.existsByEmailId("john@example.com")).thenReturn(false);
        when(repository.save(any(UserDetails.class))).thenAnswer(i -> i.getArguments()[0]);

        UserRegistrationResponse response = service.registerUser(request);

        assertNotNull(response);
        assertEquals("user registered successfully", response.getStatus());
        assertNotNull(response.getAllottedMobileNumber());
    }

    @Test
    public void testRegisterUser_Duplicate() {
        UserRegistrationRequest request = new UserRegistrationRequest("John", "123456789012", "Hyderabad", "john@example.com");

        when(repository.existsByAadhaarNumber("123456789012")).thenReturn(true);

        assertThrows(DuplicateUserException.class, () -> service.registerUser(request));
    }
}
