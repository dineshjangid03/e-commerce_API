package com.shopy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopy.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	public List<Product> findByProductName(String productName);
	
	@Query("SELECT p FROM Product p WHERE p.productName LIKE %:title%")
	public List<Product> findByProductNameLike(@Param("title") String likePattern);
	
	public List<Product> findTop5ByOrderBySoldCountDesc();

}
