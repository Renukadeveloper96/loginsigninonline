package com.in.pathshala.onlineBookStore.Dto.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineBookStore.Dto.ResponseDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignInDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignInResponseDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignupDto;
import com.in.pathshala.onlineBookStore.Dto.exceptions.AuthenticationFailException;
import com.in.pathshala.onlineBookStore.Dto.exceptions.CustomException;
import com.in.pathshala.onlineBookStore.Dto.model.AuthenticationToken;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.in.pathshala.onlineBookStore.Dto.repository.SellerRepository;
import com.in.pathshala.onlineBookStore.Dto.utils.Helper;
import com.in.pathshala.onlineBookStore.config.MessageStrings;


@Service
public class SellerService {

	Logger logger = LoggerFactory.getLogger(SellerService.class);
	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	AuthenticationService authenticationService;
	
	@Transactional
	public ResponseDto signup(SignupDto signupDto) throws NoSuchAlgorithmException {
		String message;
		String status;
	
	if(Objects.nonNull(sellerRepository.findByEmail(signupDto.getEmail()))) {
		throw new CustomException("User Already Present");
	}
	

	String encryptedpassword = signupDto.getPassword();
	encryptedpassword =hashPassword(signupDto.getPassword());
	
	Seller seller =new Seller(0,signupDto.getFirstName(),signupDto.getLastName(),
			signupDto.getEmail(),encryptedpassword,signupDto.getAddress(),signupDto.getPhone());
	sellerRepository.save(seller);
	//save to user
	
	//create the token
	final AuthenticationToken authenticationToken =new AuthenticationToken(seller);
	authenticationService.saveConfirmationToken(authenticationToken);
	ResponseDto responseDto =new ResponseDto(status="success",message="Registration successful");
	return responseDto;
}

	public Seller saveSeller(Seller seller) {
		try {
			logger.info("requesting saveProducts from ProductServiceImpl");
			return sellerRepository.save(seller);
	}catch(Exception e) {
		logger.info("try catch block in ProductRepository");
		return null;
	}
}
	public void deleteUserById(long id) {
		  sellerRepository.deleteById(id);
	}
	public Seller updateUser(Seller seller) {
		try {
			logger.info("requesting updateProducts from ProductServiceImpl");
			return sellerRepository.save(seller);
	}catch(Exception e) {
		logger.info("try catch block updateProduct in ProductRepository");
		return null;
	}
	}

	 String hashPassword(String password) throws NoSuchAlgorithmException {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	        byte[] digest = md.digest();
	        String myHash = DatatypeConverter
	                .printHexBinary(digest).toUpperCase();
	        return myHash;
	    }
	 
	 
	 public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {
	        // first find User by email
	        Seller seller = sellerRepository.findByEmail(signInDto.getEmail());
	        if(!Helper.notNull(seller)){
	            throw  new AuthenticationFailException("User Not Present");
	        }
	        try {
	            // check if password is right
	            if (!seller.getPassword().equals(hashPassword(signInDto.getPassword()))){
	                // passowrd doesnot match
	                throw  new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
	            }
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            logger.error("hashing password failed {}", e.getMessage());
	            throw new CustomException(e.getMessage());
	        }

	        AuthenticationToken token = authenticationService.getToken(seller);

	        if(!Helper.notNull(token)) {
	            // token not present
	            throw new CustomException("token not present");
	        }

	        return new SignInResponseDto ("success", token.getToken());
	    }

}
