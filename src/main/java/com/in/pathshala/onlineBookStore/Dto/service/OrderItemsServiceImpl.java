package com.in.pathshala.onlineBookStore.Dto.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.pathshala.onlineBookStore.Dto.model.OrderItem;
import com.in.pathshala.onlineBookStore.Dto.repository.OrderItemsRepository;


@Service
@Transactional
public class OrderItemsServiceImpl implements OrderItemsService{

	@Autowired 
	private OrderItemsRepository orderItemRepository;
	
	@Override
	public void addOrderedProducts(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
		
	}


}
