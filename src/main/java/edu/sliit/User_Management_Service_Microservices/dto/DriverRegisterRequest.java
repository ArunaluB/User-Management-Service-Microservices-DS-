package edu.sliit.User_Management_Service_Microservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverRegisterRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^(0\\d{9}|\\+94\\d{9})$",
            message = "Phone number must start with 0 or +94 and have correct number of digits"
    )
    private String phoneNumber;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    @NotBlank(message = "Vehicle number is required")
    private String vehicleNo;

    @NotBlank(message = "Vehicle model is required")
    private String vehicleModel;

    @NotBlank(message = "NIC number is required")
    private String nicNo;

    @NotBlank(message = "License plate is required")
    private String licencePlate;

    @NotBlank(message = "License number is required")
    private String licenceNumber;

    @NotBlank(message = "License expiry date is required")
    private String licenceExpiryDate;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Profile image is required")
    private String profileImage;

    @NotBlank(message = "Address testimony is required")
    private String addressTestimony;

    @NotBlank(message = "License image front path is required")
    private String licenseImagePathFront;

    @NotBlank(message = "License image back path is required")
    private String licenseImagePathBack;

    @NotBlank(message = "NIC image front path is required")
    private String nicImagePathFront;

    @NotBlank(message = "NIC image back path is required")
    private String nicImagePathBack;

    @NotBlank(message = "Vehicle front image path is required")
    private String vehicleFrontPath;

    @NotBlank(message = "Vehicle rear image path is required")
    private String vehicleRearPath;

    @NotBlank(message = "Vehicle side image path is required")
    private String vehicleSidePath;

}
