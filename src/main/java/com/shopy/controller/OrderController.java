package com.shopy.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopy.exception.CartException;
import com.shopy.exception.OrderException;
import com.shopy.model.Order;
import com.shopy.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService os;
	

	public Order addOrder(Order order, int cartId) throws OrderException, CartException {
		return null;
	}

	public Order viewOrder(int orderId) throws OrderException {
		return null;
	}

	public List<Order> viewOrdersviewBills(LocalDate startDate, LocalDate endDate) throws OrderException {
		return null;
	}

	public Order updateOrderStatus(int orderId, String status) throws OrderException {
		return null;
	}

	public Order deleteOrder(int orderId) throws OrderException {
		return null;
	}


}
