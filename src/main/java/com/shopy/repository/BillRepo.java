package com.shopy.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopy.model.Bill;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer>{
	
	@Query(value = "from Bill b where billDate BETWEEN :startDate AND :endDate")
	public List<Bill> billBetweenDate(@Param("startDate")LocalDate startDate,@Param("endDate")LocalDate endDate);

}
