package com.in.pathshala.onlineBookStore.Dto.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.in.pathshala.onlineBookStore.Dto.checkout.CheckoutItemDto;
import com.in.pathshala.onlineBookStore.Dto.model.Order;
import com.in.pathshala.onlineBookStore.Dto.model.Seller;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;


@Service
public interface OrderService {
	 List<Order> findAllByUserOrderByCreatedDateDesc(Seller seller);

	Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException;

	void placeOrder(Seller seller, String sessionId);

	public List<Order> listOrders(Seller seller);
	Order getOrder(long orderId);
}
