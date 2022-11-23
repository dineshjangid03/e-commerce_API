package com.shopy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CartException;
import com.shopy.exception.OrderException;
import com.shopy.model.Cart;
import com.shopy.model.Order;
import com.shopy.repository.CartRepo;
import com.shopy.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepo or;
	
	@Autowired
	private CartRepo cartRepo;
	

	@Override
	public Order addOrder(Order order, int cartId) throws OrderException, CartException {
		Optional<Cart>car=cartRepo.findById(cartId);
		if(car.isEmpty()) {
			throw new CartException("cart not found with id "+cartId);
		}
		order.setCart(car.get());
		order.setCustomer(car.get().getCustomer());
		return or.save(order);
	}

	@Override
	public Order viewOrder(int orderId) throws OrderException {
		Optional<Order>ord=or.findById(orderId);
		if(ord.isEmpty()) {
			throw new OrderException("order not found with id "+orderId);
		}
		return ord.get();
	}

	@Override
	public List<Order> viewOrdersByDate(LocalDate startDate, LocalDate endDate) throws OrderException {
		List<Order>list=or.orderBetweenDate(startDate, endDate);
		if(list.size()==0)
			throw new OrderException("no order found between "+startDate+" and "+endDate);
		return list;
	}

	@Override
	public Order updateOrderStatus(int orderId, String status) throws OrderException {
		Optional<Order>ord=or.findById(orderId);
		if(ord.isEmpty()) {
			throw new OrderException("order not found with id "+orderId);
		}
		ord.get().setStatus(status);
		return ord.get();
	}

	@Override
	public Order deleteOrder(int orderId) throws OrderException {
		Optional<Order>ord=or.findById(orderId);
		if(ord.isEmpty()) {
			throw new OrderException("order not found with id "+orderId);
		}
		Order order=ord.get();
		order.setCustomer(null);
		order.setCart(null);
		or.delete(ord.get());
		return ord.get();
	}

}
