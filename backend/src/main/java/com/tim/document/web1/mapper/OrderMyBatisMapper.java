package com.tim.document.web1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tim.document.web1.model.Order;

@Mapper
public interface OrderMyBatisMapper {
	List<Order>findAll();
	Order findById(@Param("id")Integer id);
	void insert(Order order);
}
