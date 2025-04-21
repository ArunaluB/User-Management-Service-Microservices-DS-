package edu.sliit.User_Management_Service_Microservices.servise;

import edu.sliit.User_Management_Service_Microservices.document.Driver;

import java.util.Optional;

public interface DriverService {
    Driver registerDriver(Driver driver);
    Optional<Driver> findByEmail(String email);
}
