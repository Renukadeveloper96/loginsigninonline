package com.in.pathshala.onlineBookStore.Dto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.pathshala.onlineBookStore.Dto.model.Cart;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;

public interface CartRepository1 extends JpaRepository<Cart, Integer>{

	public List<Cart> findAllBySellerOrderByCreatedDateDesc(Seller seller);
//	void deleteById(long id);
	public void deleteBySeller(Seller seller);

}

