package com.tim.document.web1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tim.document.web1.model.OrderItem;

@Mapper
public interface OrderItemMyBatisMapper {
	List<OrderItem>findAll();
	OrderItem findById(@Param("id")Integer id);
	List<OrderItem>findByOrderId(@Param("orderId")Integer orderId);
	void insert(OrderItem orderItem);
}
