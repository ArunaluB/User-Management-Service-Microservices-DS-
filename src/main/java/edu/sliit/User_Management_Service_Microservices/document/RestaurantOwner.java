package edu.sliit.User_Management_Service_Microservices.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("restaurant_owners")
public class RestaurantOwner {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String restaurantName;
    private String role = "RESTAURANT_OWNER";
}