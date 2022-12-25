package com.shopy.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.AdminException;
import com.shopy.exception.CartException;
import com.shopy.exception.CustomerException;
import com.shopy.exception.OrderException;
import com.shopy.model.Cart;
import com.shopy.model.CurrentUserSession;
import com.shopy.model.Customer;
import com.shopy.model.Order;
import com.shopy.model.Product;
import com.shopy.model.ProductDTO;
import com.shopy.repository.CartRepo;
import com.shopy.repository.CustomerRepo;
import com.shopy.repository.OrderRepo;
import com.shopy.repository.ProductRepo;
import com.shopy.repository.UserSessionRepo;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepo or;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private UserSessionRepo usRepo;
	
	@Autowired
	private CustomerRepo cr;
	
	@Autowired
	private ProductRepo prepo;
	

	@Override
	public Order addOrder(String uuid) throws OrderException, CartException {
		
		List<CurrentUserSession> extCu=usRepo.findByUuid(uuid);
		if(extCu.size()==0)
			throw new OrderException("key is not valid");
		
		Optional<Customer> c=cr.findById(extCu.get(0).getUserId());
		
		if(!c.isPresent()) {
			throw new OrderException("user not found");
		}
		
		Customer customer=c.get();
		
		Optional<Cart>car=cartRepo.findById(customer.getCart().getCartId());
		if(car.isEmpty()) {
			throw new CartException("cart not found");
		}
		
		Cart cart=car.get();
		
		if(cart.getProducts().size()==0) {
			throw new OrderException("please add product in cart");
		}
		
		Order order=new Order();
		
		order.setCustomer(customer);
		order.setOrderDate(LocalDate.now());
		order.setOrderTime(LocalTime.now());
		order.setStatus("pending");
		
		List<ProductDTO>temp=new ArrayList<>();
		
		for(ProductDTO p:cart.getProducts()) {
			if(p.getAvailableProduct()<p.getQuantity()) {
				throw new OrderException(p.getProductName()+" out of stock");
			}
			temp.add(p);
		}
		
		order.setProducts(temp);
		
		List<ProductDTO>list=cart.getProducts();
		
		list.forEach(p->{
			Product pro=prepo.findById(p.getProductId()).get();
			pro.setSoldCount(pro.getSoldCount()+p.getQuantity());
			pro.setQuantity(pro.getQuantity()-p.getQuantity());
			prepo.save(pro);
		});
		
		Order o=or.save(order);
		
		cart.getProducts().clear();
		cart.setTotalItems(0);
		cart.setTotalPrice(0);
		cartRepo.save(cart);
		
		return o;
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
		return or.save(ord.get());
	}

	@Override
	public Order deleteOrder(int orderId) throws OrderException {
		Optional<Order>ord=or.findById(orderId);
		if(ord.isEmpty()) {
			throw new OrderException("order not found with id "+orderId);
		}
		Order order=ord.get();
		order.setCustomer(null);
//		order.setCart(null);
		or.delete(ord.get());
		return ord.get();
	}

	@Override
	public List<Order> viewAllOrder() throws OrderException, AdminException {
		List<Order>list=or.findAll();
		if(list.size()==0) {
			throw new OrderException("no order found");
		}
		Collections.reverse(list);
		return list;
	}

}
