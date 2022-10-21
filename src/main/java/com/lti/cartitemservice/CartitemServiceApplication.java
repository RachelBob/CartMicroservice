package com.lti.cartitemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CartitemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartitemServiceApplication.class, args);
		System.out.println("Started CartItem Microservice...");
	}

}
