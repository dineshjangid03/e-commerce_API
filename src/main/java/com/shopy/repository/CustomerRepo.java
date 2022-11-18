package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopy.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
