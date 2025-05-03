package edu.sliit.User_Management_Service_Microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsernameDriverResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String username;
    private String profileImage;
    private boolean isVerified = false;

}
