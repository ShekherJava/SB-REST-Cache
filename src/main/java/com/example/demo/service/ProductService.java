package com.example.demo.service;

import com.example.demo.entity.Product;


public interface ProductService {
    public Product getProductById(Long id);
    
    public Product updateProduct(Product product);
    
    public void deleteProductById(Long id);
}
