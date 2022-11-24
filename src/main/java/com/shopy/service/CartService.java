package com.shopy.service;

import com.shopy.exception.CartException;
import com.shopy.exception.CustomerException;
import com.shopy.exception.ProductException;
import com.shopy.model.Cart;

public interface CartService {
	
	public Cart addCart(Cart cart,String key)throws CustomerException;
	
	public Cart viewCart(int cartId,String key)throws CartException;
	
	public Cart addItemIntoCart(int cartId, int productId,String key)throws CartException,ProductException;
	
	public Cart removeItemFromCart(int cartId, int productId,String key)throws CartException,ProductException;
	
	public Cart increaseQuantity(int cartId, int productId, int quantity,String key)throws CartException,ProductException;
	
	public Cart decreaseQuantity(int cartId, int productId, int quantity,String key)throws CartException,ProductException;
	
	public Cart clearCart(int cartId,String key)throws CartException;
	
	public Cart deleteCart(int cartId,String key)throws CartException;
	
}
