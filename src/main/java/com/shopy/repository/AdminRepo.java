package com.shopy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopy.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	public List<Admin> findByAdminMobile(String adminMobile);

}
