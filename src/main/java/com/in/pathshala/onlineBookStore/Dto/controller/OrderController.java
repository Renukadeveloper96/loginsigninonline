package com.in.pathshala.onlineBookStore.Dto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.pathshala.onlineBookStore.Dto.checkout.CheckoutItemDto;
import com.in.pathshala.onlineBookStore.Dto.checkout.StripeResponse;
import com.in.pathshala.onlineBookStore.Dto.exceptions.AuthenticationFailException;
import com.in.pathshala.onlineBookStore.Dto.exceptions.OrderNotFoundException;
import com.in.pathshala.onlineBookStore.Dto.model.Order;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.in.pathshala.onlineBookStore.Dto.service.AuthenticationService;
import com.in.pathshala.onlineBookStore.Dto.service.OrderService;
import com.in.pathshala.onlineBookStore.common.ApiResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private AuthenticationService authenticationService;

	// stripe create session API
	@PostMapping("/create-checkout-session")
	public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList)
			throws StripeException {
		// create the stripe session
		Session session = orderService.createSession(checkoutItemDtoList);
		StripeResponse stripeResponse = new StripeResponse(session.getId());
		// send the stripe session id in response
		return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
	}
	@PostMapping("/check")
	public ResponseEntity<Void>postdata(@RequestBody List<Seller>test){
		return  new ResponseEntity<Void>( HttpStatus.OK);
	}

	// place order after checkout
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> placeOrder(@RequestParam("token") String token,
			@RequestParam("sessionId") String sessionId) throws AuthenticationFailException {
		// validate token
		authenticationService.authenticate(token);
		// retrieve user
		Seller seller = authenticationService.getSeller(token);
		// place the order
		orderService.placeOrder(seller, sessionId);
		return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
	}

	// get all orders
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam("token") String token)
			throws AuthenticationFailException {
		// validate token
		authenticationService.authenticate(token);
		// retrieve user
		Seller seller = authenticationService.getSeller(token);
		// get orders
		List<Order> orderDtoList = orderService.listOrders(seller);

		return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
	}

	// get orderitems for an order
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOrderById(@PathVariable("id") long id, @RequestParam("token") String token)
			throws AuthenticationFailException {
		// validate token
		authenticationService.authenticate(token);
		try {
			Order order = orderService.getOrder(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} catch (OrderNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}
}
