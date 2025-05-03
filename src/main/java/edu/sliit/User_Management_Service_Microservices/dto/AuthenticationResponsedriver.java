package edu.sliit.User_Management_Service_Microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponsedriver {
    private String token;
    private String username;
    private String fullName;
    private String phone;
    private String email;
    private String role;
    private String profileImage;
}
