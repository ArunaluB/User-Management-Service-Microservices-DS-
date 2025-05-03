package edu.sliit.User_Management_Service_Microservices.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode = "1021";
    private String country = "Sri Lanka";
    private String zipCode;
    private String landmark = ""; // Optional landmark
    private Double latitude; // Optional for geolocation
    private Double longitude; // Optional for geolocation
}