package com.product.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name ="user-service") 
public interface UserClient {
	
	@DeleteMapping("/users/delete/{id}")

	ResponseEntity<?> deleteUser(@PathVariable("id") Long userId);

}
