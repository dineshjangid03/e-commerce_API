package com.shopy.service;

import com.shopy.exception.LoginException;
import com.shopy.model.Login;

public interface AdminLogin {
	
	public String adminLog(Login dto)throws LoginException;

	public String adminLogOut(String key)throws LoginException;

}
