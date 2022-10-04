package com.in.pathshala.onlineLoginSignup.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineLoginSignup.model.User;
import com.in.pathshala.onlineLoginSignup.repository.ProductRepository;
import com.in.pathshala.onlineLoginSignup.repository.SignUpAllUserRepository;

@Service
public class SignUpAllUserImpl implements SignUpAllUser{

Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	SignUpAllUserRepository signUpAllRepository;

	@Override
	public List<User> SignUpAll() {
		try {
			logger.info("get all user from user service");
			return signUpAllRepository.findAll();
		}catch(Exception e) {
			logger.info("try catch block in get all user");
			return null;
		}
	}

}
