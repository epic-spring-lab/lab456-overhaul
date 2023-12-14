package com.example.lab456;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Lab456Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab456Application.class, args);
	}

}
