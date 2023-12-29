package com.example.storemanagement;

import com.example.storemanagement.model.Role;
import com.example.storemanagement.model.User;
import com.example.storemanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class StoreManagementApplication {


	public static void main(String[] args) {
		SpringApplication.run(StoreManagementApplication.class, args);
	}


}
