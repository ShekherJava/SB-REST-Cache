package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository repo;

	@Cacheable( value = "products", key="#id")
	@Override
	public Product getProductById(Long id) {
		System.out.println("ProductServiceImpl::getProductById");
		Optional<Product>  opt = repo.findById(id);
		if ( opt.isPresent() )
			return opt.get();
		else
			return null;
	}

	@CachePut( value = "products", key = "#product.id")
	@Override
	public Product updateProduct(Product product) {
		return repo.save(product);
	}

	@CacheEvict( value = "products", key = "#id")
	@Override
	public void deleteProductById(Long id) {
		repo.deleteById(id);
	}

}
