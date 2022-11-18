package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopy.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
