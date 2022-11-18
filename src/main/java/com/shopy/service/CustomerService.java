package com.shopy.service;

import java.util.List;

import com.shopy.exception.CustomerException;
import com.shopy.model.Customer;
import com.shopy.model.Order;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer)throws CustomerException;
	
	public Customer viewCustomer(int customerId)throws CustomerException;
	
	public Customer updateCustomer(Customer customer)throws CustomerException;
	
	public Customer deleteCustomer(int customerId)throws CustomerException;
	
	public List<Order> viewOrders(int customerId)throws CustomerException;

}
