package edu.sliit.User_Management_Service_Microservices.controller;

import edu.sliit.User_Management_Service_Microservices.document.Driver;
import edu.sliit.User_Management_Service_Microservices.dto.AuthenticationRequest;
import edu.sliit.User_Management_Service_Microservices.dto.AuthenticationResponse;
import edu.sliit.User_Management_Service_Microservices.dto.requestDriverDto;
import edu.sliit.User_Management_Service_Microservices.servise.AuthServise;
import edu.sliit.User_Management_Service_Microservices.servise.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DriverController {

    private final DriverService driverService;
    private final AuthServise authService;

    @PostMapping("/register")
    public ResponseEntity<Driver> registerDriver(@RequestBody requestDriverDto requestDriverDto) {
        Driver savedDriver = driverService.registerDriver(requestDriverDto);
        return ResponseEntity.ok(savedDriver);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request.getUsername(), request.getPassword()));
    }

}