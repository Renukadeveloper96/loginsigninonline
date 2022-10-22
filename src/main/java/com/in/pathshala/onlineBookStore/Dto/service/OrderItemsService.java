package com.in.pathshala.onlineBookStore.Dto.service;

import org.springframework.stereotype.Service;

import com.in.pathshala.onlineBookStore.Dto.model.OrderItem;
@Service
public interface OrderItemsService {

	  public void addOrderedProducts(OrderItem orderItem);
	 
}
