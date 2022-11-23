package com.shopy.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.LoginException;
import com.shopy.model.Admin;
import com.shopy.model.CurrentAdminSession;
import com.shopy.model.Login;
import com.shopy.repository.AdminRepo;
import com.shopy.repository.AdminSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginImpl implements AdminLogin{
	
	@Autowired
	private AdminSessionRepo asr;
	
	@Autowired
	private AdminRepo arl;

	@Override
	public String adminLog(Login dto) throws LoginException {
		List<Admin>temp=arl.findByAdminMobile(dto.getMobile());
		if(temp.size()==0)
			throw new LoginException("please enter valid mobile number");
		
		Admin admin=temp.get(0);
		
		Optional<CurrentAdminSession> validation=asr.findById(admin.getAdminId());
		if(validation.isPresent()) {
			throw new LoginException("admin already logged in with this number");
		}
		
		if(admin.getAdminPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentAdminSession cas=new CurrentAdminSession();
			cas.setLocalDateTime(LocalDateTime.now());
			cas.setUserId(admin.getAdminId());
			cas.setUuid(key);
			asr.save(cas);
			return cas.toString();
		}
		throw new LoginException("please enter valid password");
	}

	@Override
	public String adminLogOut(String key) throws LoginException {
		List<CurrentAdminSession>validation=asr.findByUuid(key);
		if(validation.size()==0) {
			throw new LoginException("user not logged in with this number");
		}
		asr.delete(validation.get(0));
		return "Logged out !";
	}

}
