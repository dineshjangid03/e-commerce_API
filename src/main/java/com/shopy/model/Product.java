package com.shopy.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@NotNull(message = "Name cannot be null")
	private String productName;
	
	private Integer quantity;
	
	@NotNull(message = "price cannot be null")
	private Integer price;
	
	private Integer soldCount;
	
	private Integer rating;
	
	private String description;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;

}
