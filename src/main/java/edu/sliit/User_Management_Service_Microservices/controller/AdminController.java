package edu.sliit.User_Management_Service_Microservices.controller;

import edu.sliit.User_Management_Service_Microservices.document.Admin;
import edu.sliit.User_Management_Service_Microservices.document.Driver;
import edu.sliit.User_Management_Service_Microservices.repository.AdminRepository;
import edu.sliit.User_Management_Service_Microservices.repository.DriverRepository;
import edu.sliit.User_Management_Service_Microservices.utils.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private DriverRepository driverRepo;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        admin.setRole("ADMIN");
        adminRepo.save(admin);
        return ResponseEntity.ok("New admin created successfully");
    }

    @PostMapping("/verify-driver/{id}")
    public ResponseEntity<?> verifyDriver(@PathVariable String id) {
        Optional<Driver> driverOpt = driverRepo.findById(id);
        if (driverOpt.isPresent()) {
            Driver driver = driverOpt.get();
            driver.setVerified(true);
            driverRepo.save(driver);
            return ResponseEntity.ok("Driver verified successfully");
        }
        return ResponseEntity.badRequest().body("Driver not found");
    }

    @PostMapping("/reject-driver/{id}")
    public ResponseEntity<?> rejectDriver(@PathVariable String id) {
        if (driverRepo.existsById(id)) {
            driverRepo.deleteById(id);
            return ResponseEntity.ok("Driver registration rejected and removed");
        }
        return ResponseEntity.badRequest().body("Driver not found");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authHeader) {
        try {
            String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            String role = jwtService.extractRole(jwt);
            Boolean isVerified = jwtService.extractIsVerified(jwt);

            Map<String, Object> profile = new HashMap<>();
            profile.put("role", role);
            profile.put("isVerified", isVerified);

            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid token or missing fields");
        }
    }
}
