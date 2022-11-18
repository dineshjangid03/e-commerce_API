package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopy.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{

}
