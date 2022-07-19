package com.example.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.AuthenticationFailException;
import com.example.model.AuthenticationToken;
import com.example.model.User;
import com.example.repository.TokenRepository;

@Service
public class AuthenticationService {
	
   @Autowired
   TokenRepository tokenRepository;

	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		tokenRepository.save(authenticationToken);
		
	}

	public AuthenticationToken getToken(User findByEmail) {
		
		return tokenRepository.findByUser(findByEmail);
	}
	
	public User getUser(String token) {
		final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
		if(Objects.isNull(authenticationToken)) {
			return null;
		}
		return authenticationToken.getUser();
	}

	public void authenticate(String token) {
		
		if(Objects.isNull(token)) {
			throw new AuthenticationFailException("token is not present");
		}
	        if(Objects.isNull(getUser(token))) {
	        	throw new AuthenticationFailException("token is not valid");
	        }
	}
}
