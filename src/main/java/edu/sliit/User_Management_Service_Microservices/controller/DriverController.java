package edu.sliit.User_Management_Service_Microservices.controller;

import edu.sliit.User_Management_Service_Microservices.document.Driver;
import edu.sliit.User_Management_Service_Microservices.servise.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/register")
    public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver) {
        Driver savedDriver = driverService.registerDriver(driver);
        return ResponseEntity.ok(savedDriver);
    }
}