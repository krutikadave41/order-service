package com.example.order.controller;

import org.apache.kafka.common.Uuid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.base.dto.Order;
import com.example.base.dto.OrderEvent;
import com.example.order.kafka.OrderProducerService;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	private OrderProducerService orderProducerService;

	public OrderController(OrderProducerService orderProducerService) {
		this.orderProducerService = orderProducerService;
	}
	
	@PostMapping("/order")
	public String placeOrder(@RequestBody Order order) {
		order.setOrderId(Uuid.randomUuid().toString());
		OrderEvent orderEvent = new OrderEvent();
		orderEvent.setStatus("PENDING");
		orderEvent.setOrder(order);
		orderEvent.setMessage("Order is in Pending status. Please wait for confirmation");
		orderProducerService.sendMessage(orderEvent);
		return "Order placed successfully!";
	}
}
