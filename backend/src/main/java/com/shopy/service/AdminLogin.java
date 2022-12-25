package com.shopy.service;

import com.shopy.exception.LoginException;
import com.shopy.model.CurrentAdminSession;
import com.shopy.model.Login;

public interface AdminLogin {
	
	public CurrentAdminSession adminLog(Login dto)throws LoginException;

	public String adminLogOut(String key)throws LoginException;

}
