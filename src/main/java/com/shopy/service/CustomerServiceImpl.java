package com.shopy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CustomerException;
import com.shopy.model.Customer;
import com.shopy.model.Order;
import com.shopy.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo cr;

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		return cr.save(customer);
	}

	@Override
	public Customer viewCustomer(int customerId) throws CustomerException {
		Optional<Customer> c=cr.findById(customerId);
		
		if(c.isPresent()) {
			return c.get();
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		Optional<Customer> c=cr.findById(customer.getCustomerId());
		if(c.isPresent()) {
			return cr.save(customer);
		}
		throw new CustomerException("user not found with id : "+customer.getCustomerId());
	}

	@Override
	public Customer deleteCustomer(int customerId) throws CustomerException {
		Optional<Customer> c=cr.findById(customerId);
		if(c.isPresent()) {
			cr.delete(c.get());
			return c.get();
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

	@Override
	public List<Order> viewOrders(int customerId) throws CustomerException {
		Optional<Customer> c=cr.findById(customerId);
		if(c.isPresent()) {
			List<Order>list=c.get().getOrders();
			if(list.size()==0) {
				throw new CustomerException("order list is empty");
			}
			return list;
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

}
