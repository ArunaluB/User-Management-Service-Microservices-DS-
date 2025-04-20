package edu.sliit.User_Management_Service_Microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantOwnerRegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String restaurantName;
}