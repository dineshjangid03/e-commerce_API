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
import com.shopy.model.Login;
import com.shopy.service.AdminLogin;

@RestController
@RequestMapping("/adminlogin")
public class AdminLoginController {
	
	@Autowired
	private AdminLogin al;
	
	@PostMapping("/login")
	public ResponseEntity<String> adminLogin(@RequestBody Login dto) throws LoginException{
		String res=al.adminLog(dto);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> adminLogout(@RequestParam(required = false)String key) throws LoginException{
		String res=al.adminLogOut(key);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}

}
