package com.shopy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopy.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	public List<Product> findByProductName(String productName);

}
