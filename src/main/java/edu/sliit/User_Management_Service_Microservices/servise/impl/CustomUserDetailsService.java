package edu.sliit.User_Management_Service_Microservices.servise.impl;

import edu.sliit.User_Management_Service_Microservices.document.Driver;
import edu.sliit.User_Management_Service_Microservices.repository.DriverRepository;
import edu.sliit.User_Management_Service_Microservices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .<UserDetails>map(user -> user)
                .or(() -> driverRepository.findByEmail(username).map(driver -> {
                    if (!driver.isVerified()) {
                        throw new UsernameNotFoundException("Driver not verified by admin");
                    }
                    return driver;
                }))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}