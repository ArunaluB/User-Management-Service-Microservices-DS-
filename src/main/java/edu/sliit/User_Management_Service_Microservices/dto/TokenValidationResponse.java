package edu.sliit.User_Management_Service_Microservices.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenValidationResponse {
    private boolean valid;
    private String username;
    private String role;
    private Boolean isVerified;
}