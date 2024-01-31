package com.product.controller;

import com.product.entity.Product;
import com.product.service.ProductService;
import com.product.service.UserClient;

import com.product.service.UserWelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
    public class ProductController {

            private final ProductService productService;
            
            @Autowired
            private UserClient userClient;
            
            @Autowired
            private ProductController(ProductService productService){
                this.productService = productService;
            }

           @GetMapping("/{id}")
           public ResponseEntity<Product> getProductById(@PathVariable Long id){

               return new ResponseEntity<Product>(productService.getProductById(id) , HttpStatus.OK);
           }

           @GetMapping("/all")
            public ResponseEntity<List<Product>> getAllProducts(){
                 return new ResponseEntity<List<Product>> (productService.getAllProducts(),HttpStatus.OK);
            }

          @PostMapping
            public ResponseEntity<Product> addProduct(@RequestBody Product product){
                return new ResponseEntity<Product>(productService.addProduct(product),HttpStatus.CREATED);
           }
          
           @DeleteMapping("/deleteProduct/{id}")
            public ResponseEntity<?> deleteProduct(@PathVariable("id") Long productId){
                       productService.deleteProduct(productId);
                  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
          }
           
           @DeleteMapping("/deleteUser/{id}")
           public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId){
        	   userClient.deleteUser(userId);
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
           
           @GetMapping("/welcome")
           public ResponseEntity<String> welcome(){
        	   return new ResponseEntity<String> ("welecome to product service", HttpStatus.OK);
           }

           @Autowired
           private UserWelcomeService userWelcomeService;

           @GetMapping("/welcomeToUser")
           public ResponseEntity<String> welcomeToUser(){
        	   return new ResponseEntity<String> (userWelcomeService.welcomeToUserService().getBody() , HttpStatus.OK);
           }

}
