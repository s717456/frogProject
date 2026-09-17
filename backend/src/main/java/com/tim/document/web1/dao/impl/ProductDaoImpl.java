package com.tim.document.web1.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.tim.document.web1.dao.ProductDao;
import com.tim.document.web1.mapper.ProductMyBatisMapper;
import com.tim.document.web1.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	private final ProductMyBatisMapper productMyBatisMapper;
	
	
	public ProductDaoImpl(ProductMyBatisMapper productMyBatisMapper) {
		super();
		this.productMyBatisMapper = productMyBatisMapper;
	}


	@Override
	public List<Product>findAll(){
		return productMyBatisMapper.findAll();
	}


	@Override
	public List<Product> findPage(int offset, int size) {

		return productMyBatisMapper.findPage(offset,size);
	}


	@Override
	public Product findById(Integer id) {
		return productMyBatisMapper.findById(id);
	}


	@Override
	public void updateStock(Integer id, Integer stock) {
		 productMyBatisMapper.updateStock(id, stock);
	}


	@Override
	public int decreaseStock(Integer id, Integer quantity) {
		return productMyBatisMapper.decreaseStock(id, quantity);
	}
}
