package edu.sliit.User_Management_Service_Microservices.controller;
import edu.sliit.User_Management_Service_Microservices.document.RestaurantOwner;
import edu.sliit.User_Management_Service_Microservices.document.User;
import edu.sliit.User_Management_Service_Microservices.dto.RestaurantRequestDTO;
import edu.sliit.User_Management_Service_Microservices.repository.RestaurantOwnerRepository;
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

    private final RestaurantOwnerRepository repository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public RestaurantOwnerController(RestaurantOwnerRepository repository,
                                     UserRepository userRepository,
                                     RestTemplate restTemplate) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RestaurantRequestDTO request) {
        try {
            RestaurantOwner owner = new RestaurantOwner(
                    null,
                    request.getName(),
                    request.getEmail(),
                    request.getPhoneNumber(),
                    request.getPassword(),
                    request.getName(),
                    "RESTAURANT_OWNER"
            );
            repository.save(owner);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

            HttpEntity<RestaurantRequestDTO> requestEntity = new HttpEntity<>(request, headers);

            String url = "http://localhost:8090/api/restaurants";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                repository.delete(owner);
                return ResponseEntity.badRequest()
                        .body("Failed to register restaurant: " + response.getStatusCode());
            }

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

