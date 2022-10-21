package com.lti.cartitemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CartitemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartitemServiceApplication.class, args);
		System.out.println("Started CartItem Microservice...");
	}

}
