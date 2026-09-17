package com.tim.document.web1.dao;

import java.util.List;

import com.tim.document.web1.model.OrderItem;

public interface OrderItemDao {
	List<OrderItem>findAll();
	OrderItem findById(Integer id);
	List<OrderItem>findByOrderId(Integer orderId);
	void insert(OrderItem orderItem);
}
