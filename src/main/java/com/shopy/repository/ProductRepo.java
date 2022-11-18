package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopy.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
