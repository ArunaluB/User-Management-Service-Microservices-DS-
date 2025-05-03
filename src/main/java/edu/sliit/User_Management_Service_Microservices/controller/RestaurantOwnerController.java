package edu.sliit.User_Management_Service_Microservices.controller;
import edu.sliit.User_Management_Service_Microservices.document.User;
import edu.sliit.User_Management_Service_Microservices.dto.RestaurantRequestDTO;
import edu.sliit.User_Management_Service_Microservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/restaurant-owner")
public class RestaurantOwnerController {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public RestaurantOwnerController(
                                     UserRepository userRepository,
                                     RestTemplate restTemplate) {

        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RestaurantRequestDTO request) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

            HttpEntity<RestaurantRequestDTO> requestEntity = new HttpEntity<>(request, headers);

            String url = "http://localhost:8090/api/restaurants";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            User user = User.builder()
                    .username(request.getEmail())
                    .fullName(request.getName())
                    .password(request.getPassword())
                    .role("RESTAURANT_OWNER")
                    .isVerified(false)
                    .build();
            userRepository.save(user);

            return ResponseEntity.ok("Restaurant Owner registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Registration failed: " + e.getMessage());
        }
    }
}

