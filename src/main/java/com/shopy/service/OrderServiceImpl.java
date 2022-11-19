package com.shopy.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CartException;
import com.shopy.exception.OrderException;
import com.shopy.model.Order;
import com.shopy.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepo or;

	@Override
	public Order addOrder(Order order, int cartId) throws OrderException, CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order viewOrder(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> viewOrdersviewBills(LocalDate startDate, LocalDate endDate) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateOrderStatus(int orderId, String status) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deleteOrder(int orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

}
