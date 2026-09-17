package com.tim.document.web1.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tim.document.web1.model.OrderItem;
import com.tim.document.web1.service.OrderItemService;

@RestController
@RequestMapping("api/order-items")
public class OrderItemController {
	private final OrderItemService orderItemService;

	public OrderItemController(OrderItemService orderItemService) {
		super();
		this.orderItemService = orderItemService;
	}
	@GetMapping
	public List<OrderItem>findAll(){
		return orderItemService.findAll();
	}
	
	@GetMapping("/{id}")
	public OrderItem findById(@PathVariable Integer id) {
		return orderItemService.findById(id);
	}
}
