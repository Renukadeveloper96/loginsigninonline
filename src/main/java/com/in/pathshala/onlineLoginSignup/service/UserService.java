package com.in.pathshala.onlineLoginSignup.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineLoginSignup.Dto.ResponseDto;
import com.in.pathshala.onlineLoginSignup.Dto.user.SignInDto;
import com.in.pathshala.onlineLoginSignup.Dto.user.SignInResponseDto;
import com.in.pathshala.onlineLoginSignup.Dto.user.SignupDto;
import com.in.pathshala.onlineLoginSignup.exceptions.AuthenticationFailException;
import com.in.pathshala.onlineLoginSignup.exceptions.CustomException;
import com.in.pathshala.onlineLoginSignup.model.AuthenticationToken;
import com.in.pathshala.onlineLoginSignup.model.Product;
import com.in.pathshala.onlineLoginSignup.model.User;
import com.in.pathshala.onlineLoginSignup.repository.UserRepository;

@Service
public class UserService {
	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationService authenticationService;
	
	@Transactional
	public ResponseDto signup(SignupDto signupDto) {
		String message;
		String status;
//		ResponseDto responseDto =new ResponseDto(status="success",message="dummy response");
		//check if user already present
		if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
			throw new CustomException("user already present");
		}
		//hash the password
		
		String encryptedpassword = signupDto.getPassword();
		try {
			encryptedpassword =hashPassword(signupDto.getPassword());
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			
		}
		
		User user =new User(signupDto.getFirstName(),signupDto.getLastName(),
				signupDto.getEmail(),encryptedpassword);
		userRepository.save(user);
		
		//save to user
		
		//create the token
		final AuthenticationToken authenticationToken =new AuthenticationToken(user);
		authenticationService.saveConfirmationToken(authenticationToken);
		ResponseDto responseDto =new ResponseDto(status="success",message="user created succesfully");
		return responseDto;
	}
	
	public User saveUser(User user) {
		try {
			logger.info("requesting saveProducts from ProductServiceImpl");
			return userRepository.save(user);
	}catch(Exception e) {
		logger.info("try catch block in ProductRepository");
		return null;
	}
}

	public User updateUser(User user) {
		try {
			logger.info("requesting updateProducts from ProductServiceImpl");
			return userRepository.save(user);
	}catch(Exception e) {
		logger.info("try catch block updateProduct in ProductRepository");
		return null;
	}
	}

	private String hashPassword(String password)throws NoSuchAlgorithmException {
		MessageDigest md =MessageDigest.getInstance("MDS");
		md.update(password.getBytes());
		byte[] digest =md.digest();
		String hash = DatatypeConverter
				.printHexBinary(digest).toUpperCase();
		return hash;
	}
	public SignInResponseDto signIn(SignInDto signInDto) {
		// find user by email
		User user =userRepository.findByEmail(signInDto.getEmail());
		if(Objects.isNull(user)) {
			throw new AuthenticationFailException("user is not valid");
		}
		//hash the password
		try {
		if(user.getPassword().equals(hashPassword(signInDto.getPassword()))){
			throw new AuthenticationFailException("wrong password");
		}
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}//compare the password in DB
		
		//if password match
		AuthenticationToken token= authenticationService.getToken(user);
		if(Objects.isNull(token)) {
			throw new CustomException("token is not present");
			
		}
		String status;
		return new SignInResponseDto(status="sucess",token.getToken());
		//retrive the token
	}
}
