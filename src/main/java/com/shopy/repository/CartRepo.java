package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopy.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

}
