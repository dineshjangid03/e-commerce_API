package com.shopy.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;
	
	private LocalDate orderDate;
	
	private LocalTime orderTime;
	
	private String status;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;

	@OneToOne
	private Cart cart;
	
	@OneToOne(mappedBy = "order" ,cascade = CascadeType.ALL)
	private Bill bill;

}
