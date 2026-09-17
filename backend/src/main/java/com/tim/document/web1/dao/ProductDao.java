package com.tim.document.web1.dao;

import java.util.List;

import com.tim.document.web1.model.Product;

public interface ProductDao {
	List<Product>findAll();
	List<Product>findPage(int offset,int size);
	Product findById(Integer id);
	void updateStock(Integer id,Integer stock);
	int decreaseStock(Integer id,Integer quantity);
}



// SQL <- Mybatis <- xml  <- daoImpl <- dao <- ServiceImpl <- Service <-controller