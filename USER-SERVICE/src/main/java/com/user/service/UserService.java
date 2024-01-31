package com.user.service;

import com.user.entity.Product;
import com.user.entity.User;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface UserService {
	
    User getUserById(Long id);

    List<User> getAllUser();

    User addUser(User user);

    void deleteUser(Long userId);

	ResponseEntity<Product> getProductById(Long productId);

	ResponseEntity<List<Product>> getAllProduct();

	Product addProduct(Product product);

	String callProductWelcomeService();
}
