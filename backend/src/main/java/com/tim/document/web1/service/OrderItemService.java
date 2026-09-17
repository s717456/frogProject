package com.tim.document.web1.service;

import java.util.List;

import com.tim.document.web1.model.OrderItem;

public interface OrderItemService {
	List<OrderItem>findAll();
	OrderItem findById(Integer id);
}
