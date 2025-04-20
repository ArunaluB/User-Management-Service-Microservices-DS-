package edu.sliit.User_Management_Service_Microservices.servise.impl;

import edu.sliit.User_Management_Service_Microservices.document.Driver;
import edu.sliit.User_Management_Service_Microservices.repository.DriverRepository;
import edu.sliit.User_Management_Service_Microservices.servise.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Driver registerDriver(Driver driver) {
        driver.setPassword(passwordEncoder.encode(driver.getPassword()));
        driver.setVerified(false);
        return driverRepository.save(driver);
    }

    @Override
    public Optional<Driver> findByEmail(String email) {
        return driverRepository.findByEmail(email);
    }
}
