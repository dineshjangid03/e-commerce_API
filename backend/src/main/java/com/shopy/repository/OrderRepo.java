package com.shopy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopy.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{
	
	@Query(value = "from Order o where orderDate BETWEEN :startDate AND :endDate")
	public List<Order> orderBetweenDate(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);

}
