package com.shopy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	
	@Column(unique = true)
	private String adminMobile;
	
	@Column(unique = true)
	private String adminEmail;
	
	private String adminPassword;
	
	
}
