package com.in.pathshala.onlineBookStore.Dto.service;

import java.util.List;

import com.in.pathshala.onlineBookStore.Dto.ResponseDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignInDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignInResponseDto;
import com.in.pathshala.onlineBookStore.Dto.Seller.SignupDto;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;

public interface SellerService1 {
	List<Seller> findAll();//2
    Seller findSellerById(long id);
    public Seller saveSeller(Seller seller);
    public Seller updateSeller(Seller seller);
    public void deleteSellerById(long id);
	ResponseDto signup(SignupDto signupDto);
	SignInResponseDto signIn(SignInDto signInDto);
}
