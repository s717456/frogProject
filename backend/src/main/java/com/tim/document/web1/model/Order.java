package com.tim.document.web1.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	private Integer id;
	private Integer userId;
	private LocalDateTime orderDateTime;
	private Integer totalPrice;
	private List<OrderItem>items;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(Integer userId, LocalDateTime orderDateTime, Integer totalPrice) {
		super();
		this.userId = userId;
		this.orderDateTime = orderDateTime;
		this.totalPrice = totalPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<OrderItem> getItems() {
	    return items;
	}

	public void setItems(List<OrderItem> items) {
	    this.items = items;
	}
}
