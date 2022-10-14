package com.in.pathshala.onlineBookStore.Dto.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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



@Service
@Transactional
public class CartService {

	@Autowired
    private  CartRepository cartRepository;
	
	public CartService(){}
	 public CartService(CartRepository cartRepository) {
	        this.cartRepository = cartRepository;
	    }
	 
//	 public void addToCart(AddToCartDto addToCartDto, Book book, Seller seller){
//	        Cart cart = new Cart(book, addToCartDto.getQuantity(), seller);
//	        cartRepository.save(cart);
//	    }
	 public void addToCart(AddToCartDto addToCartDto, Book book, Seller seller){
	        Cart cart = new Cart(book, addToCartDto.getBookId(), seller);
	        cartRepository.save(cart);
	    }
	 public CartDto listCartItems(Seller seller) {
	        List<Cart> cartList = cartRepository.findAllBySellerOrderByCreatedDateDesc(seller);
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
	 public void updateCartItem(AddToCartDto cartDto, Seller seller,Book book){
	        Cart cart = cartRepository.getOne(cartDto.getBookId());
//	        cart.setQuantity(cartDto.getQuantity());
	        cart.setCreatedDate(new Date());
	        cartRepository.save(cart);
	    }
	 
	 public void deleteCartItem(int id,long sellerId) throws CartItemNotExistException {
	        if (!cartRepository.existsById(id))
	            throw new CartItemNotExistException("Cart id is invalid : " + id);
	        cartRepository.deleteById(id);

	    }

	    public void deleteCartItems(int userId) {
	        cartRepository.deleteAll();
	    }


	    public void deleteUserCartItems(Seller seller) {
	        cartRepository.deleteBySeller(seller);
	    }

}
