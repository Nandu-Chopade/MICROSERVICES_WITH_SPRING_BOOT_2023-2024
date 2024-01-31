package com.user.service;

import com.user.entity.Product;
import com.user.entity.User;
import com.user.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.http.HttpMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        try {
            return userRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            throw ex;
        }

    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Autowired
    private RestTemplate restTemplate;
    
    private final static String BASE_URL_FOR_PRODUCT = "http://localhost:8082/product";
    
    
    
	@Override
	public ResponseEntity<Product> getProductById(Long productId) {
		ResponseEntity<Product> forEntity = restTemplate.getForEntity(BASE_URL_FOR_PRODUCT +"/"+ productId, Product.class);
		return forEntity;
	}

	@Override
	public ResponseEntity<List<Product>> getAllProduct() {
		
		ParameterizedTypeReference<List<Product>> responseType = new ParameterizedTypeReference<List<Product>>(){};
		
		ResponseEntity<List<Product>> exchange = restTemplate.exchange(BASE_URL_FOR_PRODUCT + "/all",  HttpMethod.GET, null, responseType);
		return exchange;
	}

	@Override
	public Product addProduct(Product product) {
		
		ResponseEntity<Product> postForEntity = restTemplate.postForEntity(BASE_URL_FOR_PRODUCT, product, Product.class);
		
		return postForEntity.getBody();
	}

	@Override
    @CircuitBreaker(name="product-service", fallbackMethod="fallbackMethodToWelcomeProduct")
	public String callProductWelcomeService() {
		String forObject = restTemplate.getForObject(BASE_URL_FOR_PRODUCT + "/welcome", String.class);
		return forObject;
	}
	
	public String fallbackMethodToWelcomeProduct(Throwable th) {
		return "The Service is dawn please try after sometimes...";
	} 
}
