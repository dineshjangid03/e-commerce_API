package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopy.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
