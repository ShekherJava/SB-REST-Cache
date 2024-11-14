package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductRestController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping( value = "/product/{id}" , produces = "application/json")
	public ResponseEntity<Product> getById(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		if ( product != null )
			return new ResponseEntity<>(product, HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping( value = "/product/update", consumes = "application/json")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		
		Product p = productService.getProductById(product.getId());
		if( p == null )
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		Product updatedProduct = productService.updateProduct(product);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/product/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		productService.deleteProductById(id);
		return new ResponseEntity<>("Product is deleted", HttpStatus.OK);
	}

}
