package edu.sliit.User_Management_Service_Microservices.servise;

import edu.sliit.User_Management_Service_Microservices.document.Driver;
import edu.sliit.User_Management_Service_Microservices.dto.requestDriverDto;

import java.util.Optional;

public interface DriverService {
    Driver registerDriver(requestDriverDto requestDriverDto);
    Optional<Driver> findByEmail(String email);
}
