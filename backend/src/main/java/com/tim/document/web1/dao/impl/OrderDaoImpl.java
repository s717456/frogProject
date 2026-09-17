package com.tim.document.web1.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tim.document.web1.dao.OrderDao;
import com.tim.document.web1.mapper.OrderMyBatisMapper;
import com.tim.document.web1.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao{
	private final OrderMyBatisMapper orderMyBatisMapper;

	public OrderDaoImpl(OrderMyBatisMapper orderMyBatisMapper) {
		super();
		this.orderMyBatisMapper = orderMyBatisMapper;
	}
	
	@Override
	public List<Order>findAll(){
		return orderMyBatisMapper.findAll();
	}
	
	@Override
	public Order findById(Integer id) {
		return orderMyBatisMapper.findById(id);
	}

	@Override
	public void insert(Order order) {
		orderMyBatisMapper.insert(order);
	}
}
