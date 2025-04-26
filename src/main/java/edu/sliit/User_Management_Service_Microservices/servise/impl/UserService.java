package edu.sliit.User_Management_Service_Microservices.servise.impl;

import edu.sliit.User_Management_Service_Microservices.document.Address;
import edu.sliit.User_Management_Service_Microservices.document.User;
import edu.sliit.User_Management_Service_Microservices.dto.AddressDTO;
import edu.sliit.User_Management_Service_Microservices.dto.UpdateUserDetailsRequestDTO;
import edu.sliit.User_Management_Service_Microservices.dto.UserDetailsResponseDTO;
import edu.sliit.User_Management_Service_Microservices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public UserDetailsResponseDTO getUserDetailsById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return mapToResponseDTO(user);
    }

    public UserDetailsResponseDTO updateUserDetails(String id, UpdateUserDetailsRequestDTO request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getAddress() != null) user.setAddress(mapToEntity(request.getAddress()));
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        User updatedUser = userRepository.save(user);
        return mapToResponseDTO(updatedUser);
    }

    private UserDetailsResponseDTO mapToResponseDTO(User user) {
        return UserDetailsResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .address(mapToDTO(user.getAddress()))
                .role(user.getRole())
                .isVerified(user.isVerified())
                .build();
    }

    private Address mapToEntity(AddressDTO dto) {
        if (dto == null) return null;
        return Address.builder()
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getZipCode()) // assuming zipCode maps to postalCode
                .zipCode(dto.getZipCode())
                .landmark(dto.getLandmark())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .country("Sri Lanka") // Optional: default value or extract from another field if needed
                .build();
    }

    private AddressDTO mapToDTO(Address address) {
        if (address == null) return null;
        return AddressDTO.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .landmark(address.getLandmark())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();
    }
}


