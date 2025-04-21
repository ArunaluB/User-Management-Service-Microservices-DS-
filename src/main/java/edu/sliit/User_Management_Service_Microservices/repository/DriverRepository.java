package edu.sliit.User_Management_Service_Microservices.repository;

import edu.sliit.User_Management_Service_Microservices.document.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DriverRepository extends MongoRepository<Driver, String> {
    Optional<Driver> findByEmail(String email);
}
