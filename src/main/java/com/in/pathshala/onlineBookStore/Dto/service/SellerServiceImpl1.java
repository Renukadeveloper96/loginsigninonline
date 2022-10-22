package com.in.pathshala.onlineBookStore.Dto.service;

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
import org.springframework.web.bind.annotation.RequestBody;

import com.in.pathshala.onlineBookStore.Dto.ResponseDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignInDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignInResponseDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignupDto;
import com.in.pathshala.onlineBookStore.Dto.exceptions.AuthenticationFailException;
import com.in.pathshala.onlineBookStore.Dto.exceptions.CustomException;
import com.in.pathshala.onlineBookStore.Dto.model.AuthenticationToken;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.in.pathshala.onlineBookStore.Dto.repository.SellerRepository1;
import com.in.pathshala.onlineBookStore.Dto.utils.Helper;
import com.in.pathshala.onlineBookStore.config.MessageStrings;

@Service
public class SellerServiceImpl1 implements SellerService1{
	Logger logger = LoggerFactory.getLogger(SellerServiceImpl1.class);
	@Autowired
	SellerRepository1 sellerRepository1;
	@Autowired
	AuthenticationService authenticationService;
	
	@Transactional
	public ResponseDto signup(SignupDto signupDto) {
		String message;
		String status;
	
	if(Objects.nonNull(sellerRepository1.findByEmail(signupDto.getEmail()))) {
		throw new CustomException("User Already Present");
	}
	

	String encryptedpassword = signupDto.getPassword();
	try {
		encryptedpassword =hashPassword(signupDto.getPassword());
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Seller seller =new Seller(0,signupDto.getFirstName(),signupDto.getLastName(),
			signupDto.getEmail(),encryptedpassword,signupDto.getAddress(),signupDto.getPhone());
	sellerRepository1.save(seller);
	//save to user
	
	//create the token
	final AuthenticationToken authenticationToken =new AuthenticationToken(seller);
	authenticationService.saveConfirmationToken(authenticationToken);
	ResponseDto responseDto =new ResponseDto(status="success",message="Registration successful");
	return responseDto;
}
	String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }
	
	@Override
	public List<Seller> findAll() {
		try {
			logger.info("requesting findAll from SellerRepository1");
			return sellerRepository1.findAll();
		}catch(Exception e) {
			logger.error("try catch block in findAll from SellerRepository1");
			return null;
		}
	}
	@Override
	public Seller findSellerById(long id) {
		try {
			logger.info("requesting findAll from SellerRepository1");
			return sellerRepository1.findById(id).get();
		}catch(Exception e) {
			logger.error("try catch block in findAll from SellerRepository1");
			return null;
		}
	}
	@Override
	public Seller saveSeller(Seller seller) {
		try {
			logger.info("requesting saveProducts from ProductServiceImpl");
			return sellerRepository1.save(seller);
	}catch(Exception e) {
		logger.info("try catch block in ProductRepository");
		return null;
	}
	}
	@Override
	public Seller updateSeller(Seller seller) {
		try {
			logger.info("requesting updateProducts from ProductServiceImpl");
			return sellerRepository1.save(seller);
	}catch(Exception e) {
		logger.info("try catch block updateProduct in ProductRepository");
		return null;
	}
	}

	@Override
	public void deleteSellerById(long id) {
		 sellerRepository1.deleteById(id);
		
	}
	
	@Override
	public Seller findSellerByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
//	public Seller saveSeller(Seller seller) {
//		try {
//			logger.info("requesting saveProducts from ProductServiceImpl");
//			return sellerRepository1.save(seller);
//	}catch(Exception e) {
//		logger.info("try catch block in ProductRepository");
//		return null;
//	}
//}
//	public void deleteUserById(long id) {
//		  sellerRepository1.deleteById(id);
//	}
//	public Seller updateUser(Seller seller) {
//		try {
//			logger.info("requesting updateProducts from ProductServiceImpl");
//			return sellerRepository1.save(seller);
//	}catch(Exception e) {
//		logger.info("try catch block updateProduct in ProductRepository");
//		return null;
//	}
//	}
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	@Override
//	public SignInResponseDto signIn(SignInDto signInDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {
        // first find User by email
        Seller seller = sellerRepository1.findByEmail(signInDto.getEmail());
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
	
//	@Override
//	public ResponseDto signupp(@RequestBody Seller seller) {
//		String message;
//		String status;
//	
//	if(Objects.nonNull(sellerRepository1.findByEmail(seller.getEmail()))) {
//		throw new CustomException("User Already Present");
//	}
//	
//
//	String encryptedpassword = seller.getPassword();
//	try {
//		encryptedpassword =hashPassword(seller.getPassword());
//	} catch (NoSuchAlgorithmException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//	Seller seller1 =new Seller(0,seller.getFirstName(),seller.getLastName(),
//			seller.getEmail(),encryptedpassword,seller.getAddress(),seller.getPhone());
//	sellerRepository1.save(seller);
//	//save to user
//	
//	//create the token
//	final AuthenticationToken authenticationToken =new AuthenticationToken(seller);
//	authenticationService.saveConfirmationToken(authenticationToken);
//	ResponseDto responseDto =new ResponseDto(status="success",message="Registration successful");
//	return responseDto;
//}
//	String hashPassword(String password) throws NoSuchAlgorithmException {
//  MessageDigest md = MessageDigest.getInstance("MD5");
//  md.update(password.getBytes());
//  byte[] digest = md.digest();
//  String myHash = DatatypeConverter
//          .printHexBinary(digest).toUpperCase();
//  return myHash;
//}
		

}
