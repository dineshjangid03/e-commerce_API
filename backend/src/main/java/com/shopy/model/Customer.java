package com.shopy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	private String customerName;
	
	@Column(unique = true)
	private String mobile;
	
	@Email(message =  "Email is not in 'example@email.com' format")
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@Embedded
	private Address address;
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
	private Cart cart;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer" ,cascade = CascadeType.ALL)
	private List<Order> orders;
	
}
