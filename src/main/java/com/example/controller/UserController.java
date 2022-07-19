package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.SignInResponseDto;
import com.example.common.UserResponse;
import com.example.dto.SignInDto;
import com.example.dto.SignupDto;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
    UserService userService;
	
	//signup
	
	@PostMapping("/signup")
	public UserResponse signup(@RequestBody SignupDto signupDto){
		return userService.signup(signupDto);
		
	}
	
	//signIn
	
	@PostMapping("/signin")
	public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
		
		return userService.signIn(signInDto) ;
		
	}

}
