package com.shopy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopy.exception.LoginException;
import com.shopy.model.CurrentUserSession;
import com.shopy.model.Login;
import com.shopy.service.CustomerLogin;

@RestController
@RequestMapping("/userlogin")
public class UserLoginController {
	
	@Autowired
	private CustomerLogin cl;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> userLogin(@RequestBody Login dto) throws LoginException{
		CurrentUserSession res=cl.logIntoAccount(dto);
		return new ResponseEntity<CurrentUserSession>(res,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> userLogout(@RequestParam(required = false)String key) throws LoginException{
		String res=cl.logOutFromAccount(key);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}

}
