package com.product.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface UserWelcomeService {

    @GetMapping("/users/welcome")
    @CircuitBreaker(name = "user-service", fallbackMethod = "fallbackResponse")
    ResponseEntity<String> welcomeToUserService();

    default ResponseEntity<String> fallbackResponse(Throwable ex) {
        return new ResponseEntity<>("Currently, the server is down. Please try again after a while...", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
