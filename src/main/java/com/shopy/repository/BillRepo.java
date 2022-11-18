package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopy.model.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer>{

}
