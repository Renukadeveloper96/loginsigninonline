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
import com.in.pathshala.onlineBookStore.Dto.repository.SellerRepository1;
import com.in.pathshala.onlineBookStore.Dto.service.AuthenticationService;
import com.in.pathshala.onlineBookStore.Dto.service.SellerService1;

@RequestMapping("seller")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class SellerController {

	Logger logger = LoggerFactory.getLogger(SellerController.class);
	
	@Autowired
	SellerService1 sellerService1;
	
	@Autowired
    AuthenticationService authenticationService;
	
	
	@Autowired
    SellerRepository1 sellerRepository1;

	//signup:localhost:8085/user/signup
	@PostMapping("/signup")
	public ResponseDto signup(@RequestBody SignupDto signupDto) throws NoSuchAlgorithmException {
		return sellerService1.signup(signupDto);
	}

//	@PostMapping("/signupp")
//	public ResponseDto signupp(@RequestBody Seller seller) throws NoSuchAlgorithmException {
//		return sellerService1.signupp(seller);
//	}
	
	//localhost:8085/user/all?token=24d7755e-8916-4a03-b43d-4ad61ef4afe2
	@GetMapping("/all")
    public List<Seller> findAllSeller(@RequestParam("token") String token) {
        authenticationService.authenticate(token);
        return sellerRepository1.findAll();
    }
	//localhost:8085/user/all/2?token=24d7755e-8916-4a03-b43d-4ad61ef4afe2
	@GetMapping("/all/{id}")
    public Seller findUserById(@PathVariable long id ) throws AuthenticationFailException {
		return sellerRepository1.findById(id).get();
    }
	//deleteProduct:localhost:8085/seller/deleteSeller/8
	@DeleteMapping("/deleteSeller/{id}")
	public void deleteProduct(@PathVariable long id) {
	 logger.info("Deleting by id is executed");
	 sellerService1.deleteSellerById(id);
	 
}
	//signin:localhost:8085/seller/signin
	@PostMapping("/signin")
	public  SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
		return sellerService1.signIn(signInDto);
	}
	
}
