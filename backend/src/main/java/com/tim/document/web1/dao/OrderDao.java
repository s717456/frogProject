package com.tim.document.web1.dao;

import java.util.List;

import com.tim.document.web1.model.Order;

public interface OrderDao {
	List<Order>findAll();
	Order findById(Integer id);
	void insert(Order order);
}
