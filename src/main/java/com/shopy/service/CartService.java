package com.shopy.service;

import com.shopy.exception.CartException;
import com.shopy.exception.CustomerException;
import com.shopy.exception.ProductException;
import com.shopy.model.Cart;

public interface CartService {
	
	public Cart addCart(Cart cart)throws CustomerException;
	
	public Cart viewCart(int cartId)throws CartException;
	
	public Cart addItemIntoCart(int cartId, int productId)throws CartException,ProductException;
	
	public Cart removeItemFromCart(int cartId, int productId)throws CartException,ProductException;
	
	public Cart increaseQuantity(int cartId, int productId, int quantity)throws CartException,ProductException;
	
	public Cart decreaseQuantity(int cartId, int productId, int quantity)throws CartException,ProductException;
	
	public Cart clearCart(int cartId)throws CartException;
	
	public Cart deleteCart(int cartId)throws CartException;
	
}
