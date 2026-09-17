package com.tim.document.web1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tim.document.web1.dao.OrderItemDao;
import com.tim.document.web1.model.OrderItem;
import com.tim.document.web1.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	private final OrderItemDao myBatisDao;
	private final OrderItemDao jdbcDao;

	public OrderItemServiceImpl(
			@Qualifier("orderItemJdbcDaoImpl")OrderItemDao jdbcDao, 
			@Qualifier("orderItemMyBatisDaoImpl")OrderItemDao myBatisDao) {
		super();
		this.myBatisDao = myBatisDao;
		this.jdbcDao = jdbcDao;
	}

	@Override
	public List<OrderItem> findAll() {
		return myBatisDao.findAll();
	}

	@Override
	public OrderItem findById(Integer id) {
		return jdbcDao.findById(id);
	}
}
