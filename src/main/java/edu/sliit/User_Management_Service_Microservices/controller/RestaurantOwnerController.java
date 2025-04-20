package edu.sliit.User_Management_Service_Microservices.controller;

import edu.sliit.User_Management_Service_Microservices.document.RestaurantOwner;
import edu.sliit.User_Management_Service_Microservices.dto.RestaurantOwnerRegisterRequest;
import edu.sliit.User_Management_Service_Microservices.repository.RestaurantOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurant-owner")
public class RestaurantOwnerController {

    @Autowired
    private RestaurantOwnerRepository repository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RestaurantOwnerRegisterRequest request) {
        RestaurantOwner owner = new RestaurantOwner(null, request.getName(), request.getEmail(), request.getPhone(), request.getPassword(), request.getRestaurantName(), "RESTAURANT_OWNER");
        repository.save(owner);
        return ResponseEntity.ok("Restaurant Owner registered successfully");
    }
}