package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopy.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{

}
