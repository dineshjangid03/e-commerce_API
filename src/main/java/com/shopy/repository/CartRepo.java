package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopy.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{

}
