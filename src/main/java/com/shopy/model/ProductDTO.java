package com.shopy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ProductDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer dtoID;

	private String productName;
	
	private Integer productId;
	
	private String url;
	
	private Integer quantity;
	
	private Integer availableProduct;
	
	private Integer price;
	
	private String description;

}
