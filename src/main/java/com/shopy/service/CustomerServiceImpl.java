package com.shopy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shopy.exception.CustomerException;
import com.shopy.model.Customer;
import com.shopy.model.Order;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer viewCustomer(int customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteCustomer(int customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> viewOrders(int customerId) throws CustomerException {
		// TODO Auto-generated method stub
		return null;
	}

}
