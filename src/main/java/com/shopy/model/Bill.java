package com.shopy.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "bills")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;
	
	private LocalDate billDate;
	
	private LocalTime billTime;
	
	private Integer totalItem;
	
	private Integer totalPrice;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Order order;
	
}
