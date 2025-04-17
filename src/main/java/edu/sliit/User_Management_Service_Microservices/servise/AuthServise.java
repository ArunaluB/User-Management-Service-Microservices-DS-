package edu.sliit.User_Management_Service_Microservices.servise;


import edu.sliit.User_Management_Service_Microservices.document.User;
import edu.sliit.User_Management_Service_Microservices.dto.AuthenticationResponse;
import edu.sliit.User_Management_Service_Microservices.dto.RegisterRequest;

public interface AuthServise {
     AuthenticationResponse register(RegisterRequest request);
     AuthenticationResponse authenticate(String username, String password);
     User getUserByUsername(String username);
}