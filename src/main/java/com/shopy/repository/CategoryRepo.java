package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopy.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
