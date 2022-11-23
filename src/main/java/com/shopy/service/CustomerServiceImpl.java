package com.shopy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CustomerException;
import com.shopy.model.CurrentUserSession;
import com.shopy.model.Customer;
import com.shopy.model.Order;
import com.shopy.repository.CustomerRepo;
import com.shopy.repository.UserSessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private UserSessionRepo usRepo;

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
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		List<CurrentUserSession> extCu=usRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		if(extCu.get(0).getUserId()!=customer.getCustomerId())
			throw new CustomerException("invalid customer detail, please login first");
		
		Optional<Customer> c=cr.findById(customer.getCustomerId());
		if(c.isPresent()) {
			return cr.save(customer);
		}
		throw new CustomerException("user not found with id : "+customer.getCustomerId());
	}

	@Override
	public Customer deleteCustomer(int customerId, String key) throws CustomerException {
		List<CurrentUserSession> extCu=usRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		if(extCu.get(0).getUserId()!=customerId)
			throw new CustomerException("invalid customer id please fill valid id");
		
		
		Optional<Customer> c=cr.findById(customerId);
		if(c.isPresent()) {
			cr.delete(c.get());
			return c.get();
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

	@Override
	public List<Order> viewOrders(int customerId, String key) throws CustomerException {
		List<CurrentUserSession> extCu=usRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		if(extCu.get(0).getUserId()!=customerId)
			throw new CustomerException("invalid customer id please fill valid id");
		
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
