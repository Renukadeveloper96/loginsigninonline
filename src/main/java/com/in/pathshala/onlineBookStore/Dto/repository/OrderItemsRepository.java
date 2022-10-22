package com.in.pathshala.onlineBookStore.Dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.pathshala.onlineBookStore.Dto.model.OrderItem;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem,Long> {
}
