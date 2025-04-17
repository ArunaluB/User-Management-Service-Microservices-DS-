package edu.sliit.User_Management_Service_Microservices.controller;

import edu.sliit.User_Management_Service_Microservices.dto.AuthenticationRequest;
import edu.sliit.User_Management_Service_Microservices.dto.AuthenticationResponse;
import edu.sliit.User_Management_Service_Microservices.dto.RegisterRequest;

import edu.sliit.User_Management_Service_Microservices.servise.AuthServise;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthServise authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request.getUsername(), request.getPassword()));
    }

}