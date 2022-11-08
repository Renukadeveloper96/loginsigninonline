package com.in.pathshala.onlineBookStore.Dto.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineBookStore.Dto.cart.AddToCartDto;
import com.in.pathshala.onlineBookStore.Dto.cart.CartDto;
import com.in.pathshala.onlineBookStore.Dto.cart.CartItemDto;
import com.in.pathshala.onlineBookStore.Dto.exceptions.CartItemNotExistException;
import com.in.pathshala.onlineBookStore.Dto.exceptions.CustomException;
import com.in.pathshala.onlineBookStore.Dto.model.Book;
import com.in.pathshala.onlineBookStore.Dto.model.Cart;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.in.pathshala.onlineBookStore.Dto.repository.BookRepository;

import com.in.pathshala.onlineBookStore.Dto.repository.CartRepository1;
import com.in.pathshala.onlineBookStore.Dto.repository.SellerRepository1;

@Service
public class CartServiceImpl1 implements CartService1 {
	
	@Autowired
	private SellerRepository1 sellerRepository1;
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	CartRepository1 cartRepository1;
	Logger logger = LoggerFactory.getLogger(CartServiceImpl1.class);

	public CartServiceImpl1(CartRepository1 cartRepository1) {
		this.cartRepository1 = cartRepository1;
	}

	public void addToCart(AddToCartDto addToCartDto) {
		Cart cart = new Cart();
		Book savebook =bookRepository.findById(addToCartDto.getBookId()).orElseThrow(
				() -> new CustomException("BookId is not found"));
		Seller saveSeller = sellerRepository1.findById(addToCartDto.getSellerId()).orElseThrow(
				() -> new CustomException("SellerId is not found"));
		double totalAmount = savebook.getPrice() * addToCartDto.getQuantity();
		cart.setQuantity(addToCartDto.getQuantity());
		cart.setBook(savebook);
		cart.setSeller(saveSeller);
		cart.setCartAmount(totalAmount);
		cart.setCreatedDate(new Date());
		cartRepository1.save(cart);
	}

	@Override
	public CartDto listCartItems(Seller seller) {
		List<Cart> cartList = cartRepository1.findAllBySellerOrderByCreatedDateDesc(seller);
		List<CartItemDto> cartItems = new ArrayList<>();
		for (Cart cart : cartList) {
			CartItemDto cartItemDto = getDtoFromCart(cart);
			cartItems.add(cartItemDto);
		}
		double totalCost = 0;
		for (CartItemDto cartItemDto : cartItems) {
			totalCost += (cartItemDto.getBook().getPrice() * cartItemDto.getQuantity());
		}
		return new CartDto(cartItems, totalCost);
	}

	public static CartItemDto getDtoFromCart(Cart cart) {
		return new CartItemDto(cart);
	}

	@Override
	public void updateCartItem(int id, AddToCartDto cartDto) {

		Cart cart = cartRepository1.findById(id).get();
		Book savebook =bookRepository.findById(cartDto.getBookId()).get();
		Seller saveSeller = sellerRepository1.findById(cartDto.getSellerId()).get();
		double totalAmount = savebook.getPrice() * cartDto.getQuantity();
		cart.setQuantity(cartDto.getQuantity());
		cart.setBook(savebook);
		cart.setSeller(saveSeller);
		cart.setCartAmount(totalAmount);
		cart.setCreatedDate(new Date());
		cartRepository1.save(cart);
	}

	@Override
	public void deleteCartItem(int id, long sellerId) {
		if (!cartRepository1.existsById(id))
			throw new CartItemNotExistException("Cart id is invalid : " + id);
		cartRepository1.deleteById(id);
	}

//	@Override
//	public void deleteCartItems(int sellerId) {
//		 cartRepository1.deleteAll();
//		
//	}
//
	@Override
	public void deleteSellerCartItems(Seller seller) {
		cartRepository1.deleteBySeller(seller);

	}

}
