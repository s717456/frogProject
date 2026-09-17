package com.tim.document.web1.service;

import java.util.List;

import com.tim.document.web1.model.Product;

public interface ProductService {
	List<Product>findAll();
	List<Product>findPage(int page,int size);
}
