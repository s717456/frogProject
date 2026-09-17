package com.tim.document.web1.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tim.document.web1.dao.OrderItemDao;
import com.tim.document.web1.mapper.OrderItemMyBatisMapper;
import com.tim.document.web1.model.OrderItem;

@Repository
public class OrderItemMyBatisDaoImpl implements OrderItemDao{
	
	
	private final OrderItemMyBatisMapper orderItemMyBatisMapper;
	
	public OrderItemMyBatisDaoImpl(OrderItemMyBatisMapper orderItemMyBatisMapper) {
		super();
		this.orderItemMyBatisMapper = orderItemMyBatisMapper;
	}

	@Override
	public List<OrderItem>findAll(){
		return orderItemMyBatisMapper.findAll();
	}

	@Override
	public OrderItem findById(Integer id) {
		return orderItemMyBatisMapper.findById(id);
	}

	@Override
	public List<OrderItem> findByOrderId(Integer orderId) {
		return orderItemMyBatisMapper.findByOrderId(orderId);
	}

	@Override
	public void insert(OrderItem orderItem) {
		orderItemMyBatisMapper.insert(orderItem);
	}
	
}
