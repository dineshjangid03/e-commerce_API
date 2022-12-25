package com.shopy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CategoryException;
import com.shopy.exception.ProductException;
import com.shopy.model.Category;
import com.shopy.model.CurrentAdminSession;
import com.shopy.model.Product;
import com.shopy.repository.AdminSessionRepo;
import com.shopy.repository.CategoryRepo;
import com.shopy.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepo pr;
	
	@Autowired
	private CategoryRepo crepo;
	
	@Autowired
	private AdminSessionRepo adsr;

	@Override
	public Product addProduct(Product product, int categoryId, String key) throws ProductException, CategoryException {
		
		List<CurrentAdminSession> list=adsr.findByUuid(key);
		
		if(list.size()==0)
			throw new ProductException("you don't have authority to add product");
		
		Optional<Category> cat=crepo.findById(categoryId);
		if(!cat.isPresent()) {
			throw new CategoryException("category not found with id "+categoryId);
		}
		Category category=cat.get();
		category.getProducts().add(product);
		
		product.setCategory(category);
		
//		crepo.save(category);
		
		return pr.save(product);
		
//		return product;
	}

	@Override
	public Product viewProduct(int productId) throws ProductException {
		Optional<Product>p=pr.findById(productId);
		if(p.isPresent()) {
			return p.get();
		}
		throw new ProductException("product not found with id "+productId);
	}

	@Override
	public List<Product> allProduct() throws ProductException {
		List<Product>list=pr.findAll();
		if(list.size()==0) {
			throw new ProductException("list is empty");
		}
		return list;
	}

	@Override
	public Product removeProduct(int productId, String key) throws ProductException {
		
		List<CurrentAdminSession> list=adsr.findByUuid(key);
		
		if(list.size()==0)
			throw new ProductException("you don't have authority to remove product");
		
		
		Optional<Product>p=pr.findById(productId);
		if(p.isPresent()) {
			p.get().setCategory(null);
			pr.delete(p.get());
			return p.get();
		}
		throw new ProductException("product not found with id "+productId);
	}

	@Override
	public Product updateProduct(Product product, String key) throws ProductException {
		
		List<CurrentAdminSession> list=adsr.findByUuid(key);
		
		if(list.size()==0)
			throw new ProductException("you don't have authority to update product");
		
		
		Optional<Product>p=pr.findById(product.getProductId());
		if(p.isPresent()) {
			Product pro=p.get();
			
			if(product.getDescription()!="")
				pro.setDescription(product.getDescription());
			
			if(product.getPrice()!=null)
				pro.setPrice(product.getPrice());
			
			if(product.getProductName()!="")
				pro.setProductName(product.getProductName());
			
			if(product.getQuantity()!=null)
				pro.setQuantity(product.getQuantity());
			
			if(product.getUrl()!="")
				pro.setUrl(product.getUrl());
			
			return pr.save(pro);
		}
		throw new ProductException("product not found with id "+product.getProductId());
	}

	@Override
	public List<Product> productByName(String name) throws ProductException {
		List<Product>list=pr.findByProductName(name);
		if(list.size()==0) {
			throw new ProductException("product not found with name "+name);
		}
		return list;
	}

	@Override
	public List<Product> productByNameLike(String name) throws ProductException {
		List<Product>list=pr.findByProductNameLike(name);
		if(list.size()==0) {
			throw new ProductException("product not found with name "+name);
		}
		return list;
	}

	@Override
	public List<Product> top5() throws ProductException {
		List<Product>list=pr.findTop5ByOrderBySoldCountDesc();
		if(list.size()==0) {
			throw new ProductException("product not found");
		}
		return list;
	}

}
