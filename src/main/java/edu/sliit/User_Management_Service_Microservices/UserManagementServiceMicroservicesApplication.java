package edu.sliit.User_Management_Service_Microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserManagementServiceMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementServiceMicroservicesApplication.class, args);
	}

}
