package edu.sliit.User_Management_Service_Microservices.dto;

import edu.sliit.User_Management_Service_Microservices.document.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String id;
    private String token;
    private String username;
    private String fullName;
    private String phone;
    private String email;
    private String role;
    private Address address = new Address();
    private String profileImage;
}