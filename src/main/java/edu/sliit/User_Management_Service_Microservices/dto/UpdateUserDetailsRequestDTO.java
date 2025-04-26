package edu.sliit.User_Management_Service_Microservices.dto;

import lombok.Data;

@Data
public class UpdateUserDetailsRequestDTO {
    private String fullName;
    private String password;
    private AddressDTO address;
}

