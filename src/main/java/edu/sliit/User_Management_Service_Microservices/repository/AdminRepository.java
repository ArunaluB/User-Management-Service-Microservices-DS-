package edu.sliit.User_Management_Service_Microservices.repository;

import edu.sliit.User_Management_Service_Microservices.document.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByEmail(String email);
}