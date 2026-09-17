package com.tim.document.web1.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tim.document.web1.dao.OrderItemDao;
import com.tim.document.web1.model.OrderItem;

@Repository
public class OrderItemJdbcDaoImpl implements OrderItemDao{
	private final JdbcTemplate jdbcTemplate;

	public OrderItemJdbcDaoImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<OrderItem> findAll() {
		String sql="""
				
				SELECT
					id,
					order_id,
					product_id,
					quantity,
					unit_price
				FROM order_items
				""";
		return jdbcTemplate.query(sql, (rs,rowNum)->{
			
			OrderItem item=new OrderItem();
			item.setId(rs.getInt("id"));
			item.setOrderId(rs.getInt("order_id"));
			item.setProductId(rs.getInt("product_id"));
			item.setQuantity(rs.getInt("quantity"));
			item.setUnitPrice(rs.getInt("unit_price"));
			return item;
		});
	}

	@Override
	public OrderItem findById(Integer id) {
		String sql="""
				
				SELECT
					id,
					order_id,
					product_id,
					quantity,
					unit_price
				FROM order_items
				WHERE id=?
				""";
		return jdbcTemplate.queryForObject(
				sql,
				(rs,rowNum)->{
					
				OrderItem item=new OrderItem();
				item.setId(rs.getInt("id"));
				item.setOrderId(rs.getInt("order_id"));
				item.setProductId(rs.getInt("product_id"));
				item.setQuantity(rs.getInt("quantity"));
				item.setUnitPrice(rs.getInt("unit_price"));
				return item;
			},
			id
		);
	}
	@Override
	public List<OrderItem> findByOrderId(Integer orderId) {

	    String sql = """
	            SELECT
	                id,
	                order_id,
	                product_id,
	                quantity,
	                unit_price
	            FROM order_items
	            WHERE order_id = ?
	            """;

	    return jdbcTemplate.query(
	            sql,
	            (rs, rowNum) -> {
	                OrderItem item = new OrderItem();

	                item.setId(rs.getInt("id"));
	                item.setOrderId(rs.getInt("order_id"));
	                item.setProductId(rs.getInt("product_id"));
	                item.setQuantity(rs.getInt("quantity"));
	                item.setUnitPrice(rs.getInt("unit_price"));

	                return item;
	            },
	            orderId
	    );
	}
	@Override
	public void insert(OrderItem orderItem) {

	    String sql = """
	        INSERT INTO order_items
	        (order_id, product_id, quantity, unit_price)
	        VALUES (?, ?, ?, ?)
	        """;

	    jdbcTemplate.update(
	        sql,
	        orderItem.getOrderId(),
	        orderItem.getProductId(),
	        orderItem.getQuantity(),
	        orderItem.getUnitPrice()
	    );
	}
	
}
