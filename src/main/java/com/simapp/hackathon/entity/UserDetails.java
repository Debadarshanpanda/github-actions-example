package com.simapp.hackathon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    @Id
    private String id;
    private String userName;
    private String aadhaarNumber;
    private String address;
    private String emailId;
    private Long phoneNumber;
}
