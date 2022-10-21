package com.lti.cartitemservice.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="CUSTOMER-MICROSERVICE")
public interface CustomerDetailsConsumer {

	@GetMapping("/customers/uuid/{uuid}")
	public String getCustomerByUUID(@PathVariable String uuid);
}
