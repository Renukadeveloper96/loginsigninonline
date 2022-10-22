package com.in.pathshala.onlineBookStore.Dto.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineBookStore.Dto.cart.AddToCartDto;
import com.in.pathshala.onlineBookStore.Dto.cart.CartDto;
import com.in.pathshala.onlineBookStore.Dto.cart.CartItemDto;
import com.in.pathshala.onlineBookStore.Dto.exceptions.CartItemNotExistException;
import com.in.pathshala.onlineBookStore.Dto.model.Book;
import com.in.pathshala.onlineBookStore.Dto.model.Cart;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.in.pathshala.onlineBookStore.Dto.repository.CartRepository;
import com.in.pathshala.onlineBookStore.Dto.repository.CartRepository1;

@Service
public class CartServiceImpl1 implements CartService1{

	@Autowired
	CartRepository1 cartRepository1;
	Logger logger=LoggerFactory.getLogger(CartServiceImpl1.class);

	 public CartServiceImpl1(CartRepository1 cartRepository1) {
	        this.cartRepository1 = cartRepository1;
	    }

//	@Override
//	public List<Cart> findAllBySellerOrderByCreatedDateDesc() {
//		try {
//			logger.info("requesting findAllBySellerOrderByCreatedDesc from CartServiceImpl1");
//			return cartRepository1.findAll();
//		}catch(Exception e) {
//			logger.error("try catch block in findAllBySellerOrderByCreatedDesc from CartServiceImpl");
//			return null;
//		}
//	}

//	@Override
//	public void deleteBookById(long id) {
//		logger.info("requesting deleteBySeller from CartServiceImpl");
//		 cartRepository1.deleteById(id);
//		
//	}

//	@Override
//	public void addToCart(AddToCartDto addToCartDto, Book book, Seller seller) {
//		Cart cart = new Cart(book, addToCartDto.getQuantity(), seller);
//        cartRepository.save(cart);
//	}
	 public void addToCart(AddToCartDto addToCartDto, Book book, Seller seller){
	        Cart cart = new Cart(book, addToCartDto.getBookId(), seller);
	        double totalAmount= book.getPrice()*addToCartDto.getQuantity();
	        cart.setCartAmount(totalAmount);
	        cartRepository1.save(cart);
	    }

	@Override
	public CartDto listCartItems(Seller seller) {
		List<Cart> cartList = cartRepository1.findAllBySellerOrderByCreatedDateDesc(seller);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getBook().getPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }
	 public static CartItemDto getDtoFromCart(Cart cart) {
	        return new CartItemDto(cart);
	    }



	@Override
	public void updateCartItem(AddToCartDto cartDto, Seller seller, Book book) {
		Cart cart = cartRepository1.getOne(cartDto.getBookId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository1.save(cart);
	}

	@Override
	public void deleteCartItem(int id, long sellerId) {
		if (!cartRepository1.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository1.deleteById(id);
    }

	@Override
	public void deleteCartItems(int sellerId) {
		 cartRepository1.deleteAll();
		
	}

	@Override
	public void deleteSellerCartItems(Seller seller) {
		cartRepository1.deleteBySeller(seller);
		
	}
	
}
