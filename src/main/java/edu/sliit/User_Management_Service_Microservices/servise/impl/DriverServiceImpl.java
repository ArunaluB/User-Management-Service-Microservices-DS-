package edu.sliit.User_Management_Service_Microservices.servise.impl;

import edu.sliit.User_Management_Service_Microservices.document.Driver;
import edu.sliit.User_Management_Service_Microservices.document.User;
import edu.sliit.User_Management_Service_Microservices.dto.requestDriverDto;
import edu.sliit.User_Management_Service_Microservices.repository.DriverRepository;
import edu.sliit.User_Management_Service_Microservices.repository.UserRepository;
import edu.sliit.User_Management_Service_Microservices.servise.DriverService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final  RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiseimpl.class);

    private final String DRIVER_SERVICE_URL = "http://localhost:8083/api/drivermanager/api/driver";

    @Override
    public Driver registerDriver(requestDriverDto requestDriverDto) {
        requestDriverDto.setPassword(passwordEncoder.encode(requestDriverDto.getPassword()));
        requestDriverDto.setVerified(false);

        // Make a POST request to the external Driver API
        ResponseEntity<Driver> response = restTemplate.postForEntity(
                DRIVER_SERVICE_URL,
                requestDriverDto,
                Driver.class
        );

        Driver registeredDriver = response.getBody();
        logger.info("Driver registered dddddddddddddd", registeredDriver);
        logger.info("User authenticated successfully: {}", registeredDriver.getUsername());
        if (registeredDriver != null) {
            // 3. Create User object and save in local MongoDB
            User user = User.builder()
                    .username(requestDriverDto.getUsername())
                    .fullName(requestDriverDto.getFirstName())
                    .password(requestDriverDto.getPassword())
                    .role("DRIVER")  // or set from DTO if dynamic
                    .isVerified(false)
                    .build();

            userRepository.save(user);
        }
        return response.getBody();
    }

    @Override
    public Optional<Driver> findByEmail(String email) {
        return driverRepository.findByEmail(email);
    }
}
