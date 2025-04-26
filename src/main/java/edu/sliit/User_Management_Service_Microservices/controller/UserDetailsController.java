package edu.sliit.User_Management_Service_Microservices.controller;

import edu.sliit.User_Management_Service_Microservices.dto.UpdateUserDetailsRequestDTO;
import edu.sliit.User_Management_Service_Microservices.dto.UserDetailsResponseDTO;
import edu.sliit.User_Management_Service_Microservices.servise.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserDetailsController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsResponseDTO> getUserDetailsById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserDetailsById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDetailsResponseDTO> updateUserDetails(
            @PathVariable String id,
            @RequestBody UpdateUserDetailsRequestDTO request) {
        return ResponseEntity.ok(userService.updateUserDetails(id, request));
    }

}

