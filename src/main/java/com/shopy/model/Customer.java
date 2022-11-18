package com.shopy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	private String customerName;
	
	private String mobile;
	
	private String email;
	
	private String password;
	
	@Embedded
	private Address address;
	
	@OneToOne
	private Cart cart;
	
	@OneToMany(mappedBy = "customer" ,cascade = CascadeType.ALL)
	private List<Order> orders;
	
}
