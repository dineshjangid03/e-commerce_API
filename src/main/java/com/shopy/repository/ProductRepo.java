package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopy.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
