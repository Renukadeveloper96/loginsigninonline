package com.in.pathshala.onlineBookStore.Dto.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.pathshala.onlineBookStore.Dto.ResponseDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignInDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignInResponseDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignupDto;
import com.in.pathshala.onlineBookStore.Dto.exceptions.AuthenticationFailException;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.in.pathshala.onlineBookStore.Dto.repository.SellerRepository;
import com.in.pathshala.onlineBookStore.Dto.service.AuthenticationService;
import com.in.pathshala.onlineBookStore.Dto.service.SellerService;

@RequestMapping("seller")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SellerController {

	Logger logger = LoggerFactory.getLogger(SellerController.class);
	@Autowired
	SellerService sellerService;
	
	@Autowired
    AuthenticationService authenticationService;
	
	@Autowired
    SellerRepository sellerRepository;

	@PostMapping("/signup")
	public ResponseDto signup(@RequestBody SignupDto signupDto) throws NoSuchAlgorithmException {
		return sellerService.signup(signupDto);
	}
	
	@GetMapping("/all")
    public List<Seller> findAllUser(@RequestParam("token") String token) {
        authenticationService.authenticate(token);
        return sellerRepository.findAll();
    }
	@GetMapping("/all/{id}")
    public Seller findUserById(@PathVariable long id ) throws AuthenticationFailException {
		return sellerRepository.findById(id).get();
    }
	@DeleteMapping("/deleteSeller/{id}")
	public void deleteProduct(@PathVariable long id) {
	 logger.info("Deleting by id is executed");
	 sellerService.deleteUserById(id);
	 
}
	@PostMapping("/signin")
	public  SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
		return sellerService.signIn(signInDto);
	}
	
}
