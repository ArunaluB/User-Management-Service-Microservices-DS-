package edu.sliit.User_Management_Service_Microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverRegisterResponse {
    private String id;
    private Long   driverId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String vehicleType;
    private String vehicleNo;
    private String vehicleModel;
    private String nicNo;
    private String licencePlate;
    private String licenceNumber;
    private String licenceExpiryDate;
    private String password;
    private String profileImage;
    private String addressTestimony;
    private String licenseImagePathFront;
    private String licenseImagePathBack;
    private String nicImagePathFront;
    private String nicImagePathBack;
    private String vehicleFrontPath;
    private String vehicleRearPath;
    private String vehicleSidePath;
    private Boolean AccountActive;
}
