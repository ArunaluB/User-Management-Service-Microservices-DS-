package edu.sliit.User_Management_Service_Microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDriverResponse {
    private Long   driverId;
    private String username;
    private String firstName;
    private String profileImage;
    private String token;
}
