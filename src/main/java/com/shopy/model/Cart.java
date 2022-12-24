package com.shopy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "carts")
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartId;
	
	private Integer totalPrice;
	
	private Integer totalItems;
	
//	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ProductDTO>products;

}
