package com.shopy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopy.exception.CategoryException;
import com.shopy.model.Category;
import com.shopy.model.Product;
import com.shopy.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService cs;
	
	@PostMapping("/add/{uuidKey}")
	public ResponseEntity<Category> addCategory(@RequestBody Category category, @PathVariable("uuidKey") String key) throws CategoryException {
		Category c=cs.addCategory(category,key);
		return new ResponseEntity<Category>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Category> viewCategory(@PathVariable("id") int categoryId) throws CategoryException {
		Category c=cs.viewCategory(categoryId);
		return new ResponseEntity<Category>(c,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{id}/{uuidKey}")
	public ResponseEntity<Category> deleteCategory(@PathVariable("id") int categoryId, @PathVariable("uuidKey") String key) throws CategoryException {
		Category c=cs.deleteCategory(categoryId,key);
		return new ResponseEntity<Category>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Category>> allCategory() throws CategoryException {
		List<Category>c=cs.allCategory();
		return new ResponseEntity<List<Category>>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewProductByCategory/{id}")
	public ResponseEntity<List<Product>> productByCategory(@PathVariable("id") int categoryId) throws CategoryException {
		List<Product>list=cs.productByCategory(categoryId);
		return new ResponseEntity<List<Product>>(list,HttpStatus.ACCEPTED);
	}

}
