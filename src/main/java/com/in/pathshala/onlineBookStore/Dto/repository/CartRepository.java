package com.in.pathshala.onlineBookStore.Dto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in.pathshala.onlineBookStore.Dto.model.Cart;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;



public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> findAllBySellerOrderByCreatedDateDesc(Seller seller);

    List<Cart> deleteBySeller(Seller seller);
}
