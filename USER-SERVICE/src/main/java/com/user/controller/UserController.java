package com.user.controller;

import com.user.entity.Product;
import com.user.entity.User;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    
    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){

        return new ResponseEntity<User>(userService.getUserById(id) , HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser(){
        return new ResponseEntity<List<User>> (userService.getAllUser(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.addUser(user),HttpStatus.CREATED);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
    	ResponseEntity<Product> productById = userService.getProductById(id);
    	return new ResponseEntity<Product> (productById.getBody(), HttpStatus.OK);
    }
    

    @GetMapping("/product/all")
    public ResponseEntity<List<Product>> getAllProduct(){
    	ResponseEntity<List<Product>> productById = userService.getAllProduct();
    	return new ResponseEntity<List<Product>> (productById.getBody(), HttpStatus.OK);
    }
    
    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
    	Product product1 = userService.addProduct(product);
    	return new ResponseEntity<Product>(product1, HttpStatus.CREATED);
    }
    
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
 	   return new ResponseEntity<String> ("welecome to user service", HttpStatus.OK);
    }
    
    @GetMapping("/welcomeToProduct")
    public ResponseEntity<String> welcomeToProduct(){
    	String callProductWelcomeService = userService.callProductWelcomeService();
 	   return new ResponseEntity<String> (callProductWelcomeService, HttpStatus.OK);
    }
    
    
}
