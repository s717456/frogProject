package com.tim.document.web1.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tim.document.web1.dao.ProductDao;
import com.tim.document.web1.model.Product;
import com.tim.document.web1.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductDao productDao;

	
	
	public ProductServiceImpl(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public List<Product>findAll(){
		return productDao.findAll();
	}

	@Override
	public List<Product> findPage(int page, int size) {
		int safePage=Math.max(page,1);
		int safeSize=Math.max(size,1);
		int offset=(safePage-1)*safeSize;
		
		return productDao.findPage(offset, safeSize);
	}
}
