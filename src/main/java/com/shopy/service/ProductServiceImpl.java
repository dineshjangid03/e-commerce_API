package com.shopy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CategoryException;
import com.shopy.exception.ProductException;
import com.shopy.model.Product;
import com.shopy.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo pr;

	@Override
	public Product addProduct(Product product, int categoryId) throws ProductException, CategoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product viewProduct(int productId) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> allProduct() throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product removeProduct(int productId) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> productByName(String name) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

}
