package com.example.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.SignInResponseDto;
import com.example.common.UserResponse;
import com.example.dto.SignInDto;
import com.example.dto.SignupDto;
import com.example.exception.AuthenticationFailException;
import com.example.exception.CustomException;
import com.example.model.AuthenticationToken;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationService authenticationService;
	
	@Transactional
	public UserResponse signup(SignupDto signupDto) {
		//check if user is already present with email 
		if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
			//we have an user then throw exception
			throw new CustomException("User already present with email");
		}
		
		//hash the password
		String encryptedPassword = signupDto.getPassword();
		
		try {
			encryptedPassword=hashPassword(signupDto.getPassword());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//save the user
		User user=new User();
		user.setEmail(signupDto.getEmail());
		user.setFirstName(signupDto.getFirstName());
		user.setLastName(signupDto.getLastName());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
	
		//create the token
		final AuthenticationToken authenticationToken = new AuthenticationToken(user);
		authenticationService.saveConfirmationToken(authenticationToken);
		
		UserResponse response=new UserResponse("Success", "Dummy Response");
		
		return response;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes());
		byte[] digest = messageDigest.digest();
		String hash=DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	public SignInResponseDto signIn(SignInDto signInDto)  {
		//find user by email
		User findByEmail = userRepository.findByEmail(signInDto.getEmail());
		if(Objects.isNull(findByEmail)) {
			throw new AuthenticationFailException("User is not authorized");
		}
		
		//hash the password
		
		try {
			if(!findByEmail.getPassword().equals(hashPassword(signInDto.getPassword()))){
				
				throw new AuthenticationFailException("wrong password");
			}
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		//compare the password in DB
		
		//if password match
		
		AuthenticationToken token=authenticationService.getToken(findByEmail);
		
		//retrive token from DB
		if(Objects.isNull(token)) {
			
			throw new CustomException("token is not present");
		}
		return new SignInResponseDto("success", token.getToken());
	}

}
