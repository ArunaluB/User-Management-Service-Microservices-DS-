package edu.sliit.User_Management_Service_Microservices.servise.impl;

import edu.sliit.User_Management_Service_Microservices.document.Driver;
import edu.sliit.User_Management_Service_Microservices.document.User;
import edu.sliit.User_Management_Service_Microservices.dto.AuthenticationResponse;
import edu.sliit.User_Management_Service_Microservices.dto.RegisterRequest;
import edu.sliit.User_Management_Service_Microservices.repository.DriverRepository;
import edu.sliit.User_Management_Service_Microservices.repository.UserRepository;
import edu.sliit.User_Management_Service_Microservices.servise.AuthServise;
import edu.sliit.User_Management_Service_Microservices.utils.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        var userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            var user = userOpt.get();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .username(user.getUsername())
                    .build();
        }

        var driverOpt = driverRepository.findByEmail(username);

        if (driverOpt.isPresent()) {
            var driver = driverOpt.get();
            if (!driver.isVerified()) {
                throw new UsernameNotFoundException("Driver not verified by admin");
            }
            var jwtToken = jwtService.generateToken(driver);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .username(driver.getUsername())
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
