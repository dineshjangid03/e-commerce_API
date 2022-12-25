package com.shopy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopy.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	
	@Query("select c from Customer cu join cu.cart c where cu.customerId = ?1")
	public Cart findByCustomerId(Integer customerId);
	
}
