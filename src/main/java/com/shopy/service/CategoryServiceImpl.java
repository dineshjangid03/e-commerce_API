package com.shopy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CategoryException;
import com.shopy.model.Category;
import com.shopy.model.Product;
import com.shopy.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo cr;

	@Override
	public Category addCategory(Category category) throws CategoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category viewCategory(int categoryId) throws CategoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category deleteCategory(int categoryId) throws CategoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> allCategory() throws CategoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> productByCategory(int categoryId) throws CategoryException {
		// TODO Auto-generated method stub
		return null;
	}

}
