package com.in.pathshala.onlineBookStore.Dto.service;

import com.in.pathshala.onlineBookStore.Dto.cart.AddToCartDto;
import com.in.pathshala.onlineBookStore.Dto.cart.CartDto;
import com.in.pathshala.onlineBookStore.Dto.model.Book;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;


public interface CartService1 {

//	public void deleteBookById(long id);
	public void addToCart(AddToCartDto addToCartDto);
	public CartDto listCartItems(Seller seller);
//	void deleteById(long id); 
	public void updateCartItem(int id,AddToCartDto cartDto);
	public void deleteCartItem(int id,long sellerId) ;
//	public void deleteCartItems(int sellerId);
	public void deleteSellerCartItems(Seller seller);

	    
}
