package edu.sliit.User_Management_Service_Microservices.servise.impl;

import edu.sliit.User_Management_Service_Microservices.document.User;
import edu.sliit.User_Management_Service_Microservices.dto.AuthenticationResponse;
import edu.sliit.User_Management_Service_Microservices.dto.RegisterRequest;
import edu.sliit.User_Management_Service_Microservices.dto.TokenValidationResponse;
import edu.sliit.User_Management_Service_Microservices.repository.DriverRepository;
import edu.sliit.User_Management_Service_Microservices.repository.UserRepository;
import edu.sliit.User_Management_Service_Microservices.servise.AuthServise;
import edu.sliit.User_Management_Service_Microservices.utils.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthServiseimpl implements AuthServise {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .phone(request.getPhone())
                .email(request.getEmail())
                .role("USER") // Default role; adjust as needed
                .isVerified(false) // Default to false; set to true if verified during registration
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(String username, String rawPassword) {
        var userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        var user = userOpt.get();

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        if (!user.isEnabled()) {
            throw new IllegalStateException("User account is not verified");
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public Mono<TokenValidationResponse> validateToken(String token) {
        return jwtService.validateToken(token);
    }
}