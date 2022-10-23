package com.lti.cartitemservice.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-microservice-ecommerce", url="http://localhost:8081/customer-microservice-ecommerce")
public interface CustomerDetailsConsumer {

	@GetMapping("/customers/uuid/{uuid}")
	public String getCustomerByUUID(@PathVariable String uuid);
}
