package edu.sliit.User_Management_Service_Microservices.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "driver")
public class Driver implements UserDetails {
    @Id
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
    private String vehicleColor;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
