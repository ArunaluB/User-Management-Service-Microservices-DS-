package edu.sliit.User_Management_Service_Microservices.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("admins")
public class Admin {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role = "ADMIN";
}