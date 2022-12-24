package com.shopy.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CustomerException;
import com.shopy.model.Address;
import com.shopy.model.Cart;
import com.shopy.model.CurrentUserSession;
import com.shopy.model.Customer;
import com.shopy.model.Order;
import com.shopy.repository.CartRepo;
import com.shopy.repository.CustomerRepo;
import com.shopy.repository.UserSessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private UserSessionRepo usRepo;
	
	@Autowired
	private CartRepo cartR;

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		Customer saved=cr.save(customer);
		Cart cart=new Cart();
		cart.setCustomer(saved);
		cartR.save(cart);
		customer.setCart(cart);
		return saved;
		
	}

	@Override
	public Customer viewCustomer(String key) throws CustomerException {
		List<CurrentUserSession> extCu=usRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		Optional<Customer> c=cr.findById(extCu.get(0).getUserId());
		
		if(c.isPresent()) {
			return c.get();
		}
		throw new CustomerException("user not found");
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		List<CurrentUserSession> extCu=usRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
//		if(extCu.get(0).getUserId()!=customer.getCustomerId())
//			throw new CustomerException("invalid customer detail, please login first");
//		
		Optional<Customer> c=cr.findById(extCu.get(0).getUserId());
		
		if(!c.isPresent()) {
			throw new CustomerException("user not found");
		}
		
		Customer pre=c.get();
		Address preA=pre.getAddress();
		
		Address newA=customer.getAddress();
		if(newA.getCity()!=null) {
			preA.setCity(newA.getCity());
		}
		if(newA.getCountry()!=null) {
			preA.setCountry(newA.getCountry());		
		}
		if(newA.getPincode()!=null) {
			preA.setPincode(newA.getPincode());
		}
		if(newA.getState()!=null) {
			preA.setState(newA.getState());
		}
		
		if(customer.getCustomerName()!=null){
			pre.setCustomerName(customer.getCustomerName());
		}
		if(customer.getEmail()!=null){
			pre.setEmail(customer.getEmail());
		}
		if(customer.getMobile()!=null){
			pre.setMobile(customer.getMobile());
		}
		if(customer.getPassword()!=null){
			pre.setPassword(customer.getPassword());
		}
		
		pre.setAddress(preA);
		return cr.save(pre);
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
	public List<Order> viewOrders(String key) throws CustomerException {
		List<CurrentUserSession> extCu=usRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");

		Optional<Customer> c=cr.findById(extCu.get(0).getUserId());
		if(c.isPresent()) {
			List<Order>list=c.get().getOrders();
			if(list.size()==0) {
				throw new CustomerException("order list is empty");
			}
			Collections.reverse(list);
			return list;
		}
		throw new CustomerException("user not found");
	}

}
