package com.shopy.model;

import lombok.Data;

@Data
public class Address {
	
	private Integer id;
	private String city;
	private String state;
	private String country;
	private String pincode;

}
