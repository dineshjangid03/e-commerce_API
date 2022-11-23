package com.shopy.service;

import com.shopy.exception.LoginException;
import com.shopy.model.Login;

public interface CustomerLogin {
	
	public String logIntoAccount(Login dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
