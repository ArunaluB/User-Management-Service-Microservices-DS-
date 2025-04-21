package edu.sliit.User_Management_Service_Microservices.repository;

import edu.sliit.User_Management_Service_Microservices.document.RestaurantOwner;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface RestaurantOwnerRepository extends MongoRepository<RestaurantOwner, String> {
    Optional<RestaurantOwner> findByEmail(String email);
}