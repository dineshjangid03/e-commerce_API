package com.shopy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopy.exception.CategoryException;
import com.shopy.exception.ProductException;
import com.shopy.model.Product;
import com.shopy.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService ps;
	
	@PostMapping("/add/{categoryId}")
	public ResponseEntity<Product> addProduct(@RequestBody Product product, @PathVariable("categoryId") int categoryId) throws ProductException, CategoryException {
		Product p=ps.addProduct(product, categoryId);
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{productId}")
	public ResponseEntity<Product> viewProduct(@PathVariable("productId") int productId) throws ProductException {
		Product p=ps.viewProduct(productId);
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewAllProduct")
	public ResponseEntity<List<Product>> allProduct() throws ProductException {
		List<Product>list=ps.allProduct();
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<Product> removeProduct(@PathVariable("productId") int productId) throws ProductException {
		Product p=ps.removeProduct(productId);
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductException {
		Product p=ps.updateProduct(product);
		return new ResponseEntity<Product>(p,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/productByName/{name}")
	public ResponseEntity<List<Product>> productByName(@PathVariable("name") String name) throws ProductException {
		List<Product>list=ps.productByName(name);
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}
	

}
