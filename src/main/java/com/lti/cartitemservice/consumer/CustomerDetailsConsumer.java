package com.lti.cartitemservice.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="${feign.client.name}", url="${feign.client.microservice.endpoint}")
public interface CustomerDetailsConsumer {

	@GetMapping("/uuid/{uuid}")
	public String getCustomerByUUID(@PathVariable String uuid);
}
