package com.tim.document.web1.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tim.document.web1.dao.OrderDao;
import com.tim.document.web1.dao.OrderItemDao;
import com.tim.document.web1.dao.ProductDao;
import com.tim.document.web1.model.Order;
import com.tim.document.web1.model.OrderItem;
import com.tim.document.web1.model.Product;
import com.tim.document.web1.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	private final OrderDao orderDao;
	private final OrderItemDao orderItemDao;
	private final ProductDao productDao;

	public OrderServiceImpl(
			OrderDao orderDao,
			@Qualifier("orderItemMyBatisDaoImpl")OrderItemDao orderItemDao,
			ProductDao productDao) {
		super();
		this.orderDao = orderDao;
		this.orderItemDao = orderItemDao;
		this.productDao=productDao;
	}
	
	@Override
	public List<Order>findAll(){
		List<Order>orders=orderDao.findAll();
		for(Order order:orders) {
			List<OrderItem>items=
					orderItemDao.findByOrderId(order.getId());
			order.setItems(items);
		}
		return orders;
	}
	
	@Override
	public Order findById(Integer id) {
		
		Order order=orderDao.findById(id);
		List<OrderItem>items=orderItemDao.findByOrderId(id);
		order.setItems(items);
		
		return order;
	}

	@Transactional
	@Override
	public void createOrder(Order order) {
		int totalprice=0;
		
		//檢查庫存
		for (OrderItem item : order.getItems()) {

	        Product product =
	                productDao.findById(item.getProductId());

	        if (product == null) {
	            throw new RuntimeException("商品不存在");
	        }

	        if (item.getQuantity() == null ||
	                item.getQuantity() <= 0) {
	            throw new RuntimeException("商品數量錯誤");
	        }

	        item.setUnitPrice(product.getPrice());

	        totalprice +=
	                product.getPrice() * item.getQuantity();
	    }
		
		//建立訂單基本資料
		order.setOrderDateTime(LocalDateTime.now());
		order.setTotalPrice(totalprice);
		
		//新增訂單
		orderDao.insert(order);
		
		//新增明細
		for (OrderItem item : order.getItems()) {

			Product product = productDao.findById(item.getProductId());

			item.setOrderId(order.getId());

			// 直接讓 SQL 嘗試扣庫存
			int affectedRows =
			        productDao.decreaseStock(
			                item.getProductId(),
			                item.getQuantity()
			        );

			if (affectedRows == 0) {
			    throw new RuntimeException(
			            product.getName() + " 庫存不足"
			    );
			}

			// 庫存扣成功後再新增明細
			orderItemDao.insert(item);
	    }
	}
	
}
