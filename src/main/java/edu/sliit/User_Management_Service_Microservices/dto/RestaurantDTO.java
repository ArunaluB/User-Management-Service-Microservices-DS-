package edu.sliit.User_Management_Service_Microservices.dto;

import edu.sliit.User_Management_Service_Microservices.document.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDTO {
    private String name;
    private Address address;
    private String cuisineType;
    private String phoneNumber;
    private String email;
    private List<String> operatingHours;
    private double averageRating;
    private double deliveryFee;
    private int estimatedDeliveryTime;
    private List<String> imageUrls;
    private String logoUrl;
    private boolean isPromoted;
    private List<String> dietaryPreferences;
}
