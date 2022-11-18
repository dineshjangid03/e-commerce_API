package com.shopy.service;

import java.util.List;

import com.shopy.exception.CategoryException;
import com.shopy.model.Category;
import com.shopy.model.Product;


public interface CategoryService {
	
	public Category addCategory(Category category)throws CategoryException;
	
	public Category viewCategory(int categoryId)throws CategoryException;
	
	public Category deleteCategory(int categoryId)throws CategoryException;
	
	public List<Category> allCategory()throws CategoryException;
	
	public List<Product> productByCategory(int categoryId)throws CategoryException;

}
