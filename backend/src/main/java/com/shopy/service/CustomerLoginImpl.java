package com.shopy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.LoginException;
import com.shopy.model.CurrentUserSession;
import com.shopy.model.Customer;
import com.shopy.model.Login;
import com.shopy.repository.CustomerRepo;
import com.shopy.repository.UserSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerLoginImpl implements CustomerLogin{

	@Autowired
	private UserSessionRepo usr;
	
	@Autowired
	private CustomerRepo crl;
	
	@Override
	public CurrentUserSession logIntoAccount(Login dto) throws LoginException {
		List<Customer> list=crl.findByMobile(dto.getMobile());
		
		if(list.size()==0) {
			throw new LoginException("please enter valid mobil number");
		}
		
		Customer customer=list.get(0);
		Optional<CurrentUserSession> validation=usr.findById(customer.getCustomerId());
		
		if(validation.isPresent()) {
			if(customer.getPassword().equals(dto.getPassword())) {
				return validation.get();
			}
			throw new LoginException("user already logged in eith this number");
		}
		
		if(customer.getPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentUserSession cus=new CurrentUserSession();
			cus.setUserId(customer.getCustomerId());
			cus.setUuid(key);
			cus.setLocalDateTime(LocalDateTime.now());
			usr.save(cus);
			return cus;
		}
		
		throw new LoginException("please enter valid password");
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		List<CurrentUserSession> validation=usr.findByUuid(key);
		if(validation.size()==0) {
			throw new LoginException("user not logged in with this number");
		}
		usr.delete(validation.get(0));
		return "Logged out !";
	}

}
