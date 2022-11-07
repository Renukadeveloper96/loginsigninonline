package com.in.pathshala.onlineBookStore.Dto.service;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineBookStore.Dto.cart.CartDto;
import com.in.pathshala.onlineBookStore.Dto.cart.CartItemDto;
import com.in.pathshala.onlineBookStore.Dto.checkout.CheckoutItemDto;
import com.in.pathshala.onlineBookStore.Dto.exceptions.OrderNotFoundException;
import com.in.pathshala.onlineBookStore.Dto.model.Book;
import com.in.pathshala.onlineBookStore.Dto.model.Order;
import com.in.pathshala.onlineBookStore.Dto.model.OrderItem;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.in.pathshala.onlineBookStore.Dto.repository.BookRepository;
import com.in.pathshala.onlineBookStore.Dto.repository.OrderItemsRepository;
import com.in.pathshala.onlineBookStore.Dto.repository.OrderRepository;
import com.in.pathshala.onlineBookStore.Dto.repository.SellerRepository1;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CartService1 cartService1;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderItemsRepository orderItemsRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private SellerRepository1 sellerRepository1;

//	@Override
//	public List<Order> findAllByUserOrderByCreatedDateDesc(Seller seller) {
//		return orderRepository.findAllBySellerOrderByCreatedDateDesc(seller);
//	}

	private String baseURL = "https://infallible-swartz-b50174.netlify.app";

	private String apiKey = "STRIPE SECRET KEY";

	SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
		return SessionCreateParams.LineItem.PriceData.builder().setCurrency("usd")
				.setUnitAmount((long) (checkoutItemDto.getPrice() * 100))
				.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
						.setName(checkoutItemDto.getBookName()).build())
				.build();
	}

	SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
		return SessionCreateParams.LineItem.builder()
				// set price for each product
				.setPriceData(createPriceData(checkoutItemDto))
				// set quantity for each product
				.setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity()))).build();
	}

	// create session from list of checkout items
	public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {

		// supply success and failure url for stripe
		String successURL = baseURL + "payment/success";
		String failedURL = baseURL + "payment/failed";

		// set the private key
		Stripe.apiKey = apiKey;

		List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<>();

		// for each product compute SessionCreateParams.LineItem
		for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCreatedDate(new Date());
			orderItem.setQuantity(checkoutItemDto.getQuantity());
			Book book = bookRepository.findById(checkoutItemDto.getBookId()).get();
			orderItem.setBook(book);
			
			// add to order item list
			orderItemsRepository.save(orderItem);
			sessionItemsList.add(createSessionLineItem(checkoutItemDto));
		}

		// build the session param
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT).setCancelUrl(failedURL).addAllLineItem(sessionItemsList)
				.setSuccessUrl(successURL).build();
		return Session.create(params);
	}

	public void placeOrder(Seller seller, String sessionId) {
		// first let get cart items for the user
		CartDto cartDto = cartService1.listCartItems(seller);

		List<CartItemDto> cartItemDtoList = cartDto.getcartItems();

		// create the order and save it
		Order newOrder = new Order();
		newOrder.setCreatedDate(new Date());
		newOrder.setSessionId(sessionId);
		newOrder.setSeller(seller);
		newOrder.setTotalPrice(cartDto.getTotalCost());
		orderRepository.save(newOrder);

		for (CartItemDto cartItemDto : cartItemDtoList) {
			// create orderItem and save each one
			OrderItem orderItem = new OrderItem();
			orderItem.setCreatedDate(new Date());
			orderItem.setPrice(cartItemDto.getBook().getPrice());
			orderItem.setBook(cartItemDto.getBook());
			orderItem.setQuantity(cartItemDto.getQuantity());
			orderItem.setOrder(newOrder);
			orderItem.setSeller(seller);	
			// add to order item list
			orderItemsRepository.save(orderItem);
		}
		//
		cartService1.deleteSellerCartItems(seller);
	}

	@Override
	public List<Order> listOrders(Seller seller) {
		return orderRepository.findAllBySellerIdOrderByCreatedDateDesc(seller);
	}

	public Order getOrder(long orderId) throws OrderNotFoundException {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent()) {
			return order.get();
		}
		throw new OrderNotFoundException("Order not found");
	}

	@Override
	public List<Order> findAllByUserOrderByCreatedDateDesc(Seller seller) {
		// TODO Auto-generated method stub
		return null;
	}



}
