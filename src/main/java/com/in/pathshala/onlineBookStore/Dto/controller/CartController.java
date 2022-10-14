package com.in.pathshala.onlineBookStore.Dto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.pathshala.onlineBookStore.Dto.cart.AddToCartDto;
import com.in.pathshala.onlineBookStore.Dto.cart.CartDto;
import com.in.pathshala.onlineBookStore.Dto.exceptions.AuthenticationFailException;
import com.in.pathshala.onlineBookStore.Dto.exceptions.BookNotExistException;
import com.in.pathshala.onlineBookStore.Dto.exceptions.CartItemNotExistException;
import com.in.pathshala.onlineBookStore.Dto.model.Book;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.in.pathshala.onlineBookStore.Dto.repository.SellerRepository;
import com.in.pathshala.onlineBookStore.Dto.service.AuthenticationService;
import com.in.pathshala.onlineBookStore.Dto.service.BookService;
import com.in.pathshala.onlineBookStore.Dto.service.CartService;
import com.in.pathshala.onlineBookStore.common.ApiResponse;




@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
    private CartService cartService;
	
	@Autowired
	private SellerRepository sellerRepository;

    @Autowired
    private BookService bookService;
	 @Autowired
	    private AuthenticationService authenticationService;
	 
	 @PostMapping("/add")
	    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
	                                                 @RequestParam("token") String token) throws AuthenticationFailException, BookNotExistException {
	        authenticationService.authenticate(token);
	        Seller seller = sellerRepository.findSellerById(addToCartDto.getSellerId());
	        Book book = bookService.findBookById(addToCartDto.getBookId());
	        System.out.println("book to add"+  book.getName());
	        cartService.addToCart(addToCartDto, book, seller);
	        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
	    }
	 @GetMapping("/")
	    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
	        authenticationService.authenticate(token);
	        Seller seller = authenticationService.getSeller(token);
	        CartDto cartDto = cartService.listCartItems(seller);
	        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	    }
	    @PutMapping("/update/{cartItemId}")
	    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
	                                                      @RequestParam("token") String token) throws AuthenticationFailException,BookNotExistException {
	        authenticationService.authenticate(token);
	        Seller seller = authenticationService.getSeller(token);
	        Book book = bookService.findBookById(cartDto.getBookId());
	        cartService.updateCartItem(cartDto, seller,book);
	        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
	    }

	    @DeleteMapping("/delete/{cartItemId}")
	    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,@RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
	        authenticationService.authenticate(token);
	        long sellerId = authenticationService.getSeller(token).getId();
	        cartService.deleteCartItem(itemID, sellerId);
	        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
	    }
}
