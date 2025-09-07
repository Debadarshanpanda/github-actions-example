package com.simapp.hackathon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    @NotBlank(message = "User name must not be blank")
    private String userName;

    @NotNull(message = "Aadhaar number must not be null")
    @Pattern(regexp = "\\d{12}", message = "Aadhaar number must be exactly 12 digits")
    private String aadhaarNumber;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email ID must not be blank")
    private String emailId;
}
