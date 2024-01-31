package com.product.service;

import com.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product addProduct(Product product);

    void deleteProduct(Long productId);
}

