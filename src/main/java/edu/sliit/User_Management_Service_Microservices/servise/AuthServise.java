package edu.sliit.User_Management_Service_Microservices.servise;


import edu.sliit.User_Management_Service_Microservices.document.User;
import edu.sliit.User_Management_Service_Microservices.dto.AuthenticationResponse;
import edu.sliit.User_Management_Service_Microservices.dto.RegisterRequest;
import edu.sliit.User_Management_Service_Microservices.dto.TokenValidationResponse;
import reactor.core.publisher.Mono;

public interface AuthServise {
     AuthenticationResponse register(RegisterRequest request);
     AuthenticationResponse registerAdmin(RegisterRequest request);
     AuthenticationResponse authenticate(String username, String password);
     User getUserByUsername(String username);
     Mono<TokenValidationResponse> validateToken(String token);
    // public String Token(String username ,String password);
}