package com.shopy.service;

import java.util.List;

import com.shopy.exception.CustomerException;
import com.shopy.model.Customer;
import com.shopy.model.Order;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer)throws CustomerException;
	
	public Customer viewCustomer(String key)throws CustomerException;
	
	public Customer updateCustomer(Customer customer, String key)throws CustomerException;
	
	public Customer deleteCustomer(int customerId, String key)throws CustomerException;
	
	public List<Order> viewOrders(String key)throws CustomerException;

}
