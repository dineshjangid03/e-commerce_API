package com.shopy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopy.exception.CategoryException;
import com.shopy.model.Category;
import com.shopy.model.Product;

@Service
public class CategoryServiceImpl implements CategoryService{

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
