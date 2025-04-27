package edu.sliit.User_Management_Service_Microservices.controller;
import edu.sliit.User_Management_Service_Microservices.dto.*;
import edu.sliit.User_Management_Service_Microservices.servise.AuthServise;
import edu.sliit.User_Management_Service_Microservices.servise.impl.AuthServiseImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthServise authService;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiseImpl.class);

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @Valid @RequestBody RegisterRequest request
    ) {
        return new ResponseEntity<>(authService.registerAdmin(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @Valid @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authService.authenticate(request.getUsername(), request.getPassword()));
    }

    @PostMapping("/verify")
    public ResponseEntity<TokenValidationResponse> verifyToken(@RequestBody TokenRequest request) {
        String token = request.getToken();
        logger.info("Verifying token: {}", token);
        try {
            TokenValidationResponse response = authService.validateToken(token).block();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Token validation failed", e);
            return ResponseEntity.ok(TokenValidationResponse.builder().valid(false).build());
        }
    }

}