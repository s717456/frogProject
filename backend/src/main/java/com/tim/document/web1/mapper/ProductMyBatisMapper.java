package com.tim.document.web1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tim.document.web1.model.Product;

@Mapper				//Mapper = 負責把 Java 的方法和 SQL 指令連接起來。
public interface ProductMyBatisMapper {
	List<Product>findAll();
	List<Product>findPage(
			@Param("offset")int offset,
			@Param("size")int size
	);
	Product findById(@Param("id")Integer id);
	void updateStock(
			@Param("id")Integer id,
			@Param("stock")Integer stock
	);
	int decreaseStock(
			@Param("id")Integer id,
			@Param("quantity")Integer quantity
	);
}
