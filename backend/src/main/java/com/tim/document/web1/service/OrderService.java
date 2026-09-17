package com.tim.document.web1.service;

import java.util.List;

import com.tim.document.web1.model.Order;

public interface OrderService {
	List<Order>findAll();
	Order findById(Integer id);
	void createOrder(Order order);
	
}
