package com.simapp.hackathon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simapp.hackathon.dto.UserRegistrationRequest;
import com.simapp.hackathon.dto.UserRegistrationResponse;
import com.simapp.hackathon.service.UserRegistrationService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRegistrationController.class)
public class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRegistrationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegisterUser_Success() throws Exception {
        UserRegistrationRequest request = new UserRegistrationRequest(
                "John", "123456789012", "Hyderabad", "john@example.com"
        );

        UserRegistrationResponse response = new UserRegistrationResponse("user registered successfully", 9876543210L);


        Mockito.when(service.registerUser(any(UserRegistrationRequest.class))).thenReturn(response);

        mockMvc.perform(post("/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("user registered successfully"))
                .andExpect(jsonPath("$.allottedMobileNumber").value(9876543210L));
    }
}
