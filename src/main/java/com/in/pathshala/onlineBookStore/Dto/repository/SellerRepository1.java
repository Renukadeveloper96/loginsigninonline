package com.in.pathshala.onlineBookStore.Dto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.pathshala.onlineBookStore.Dto.model.Cart;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;

public interface SellerRepository1 extends JpaRepository<Seller,Long>{

	Seller findByEmail(String email);

}
