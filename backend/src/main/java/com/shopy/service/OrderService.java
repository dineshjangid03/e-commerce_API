package com.shopy.service;

import java.time.LocalDate;
import java.util.List;

import com.shopy.exception.AdminException;
import com.shopy.exception.CartException;
import com.shopy.exception.OrderException;
import com.shopy.model.Order;

public interface OrderService {
	
	public Order addOrder(String uuid)throws OrderException,CartException;
	
	public Order viewOrder(int orderId)throws OrderException;
	
	public List<Order> viewOrdersByDate(LocalDate startDate, LocalDate endDate)throws OrderException;
	
	public Order updateOrderStatus(int orderId, String status)throws OrderException;
	
	public Order deleteOrder(int orderId)throws OrderException;
	
	public List<Order> viewAllOrder()throws OrderException, AdminException;
	
}
