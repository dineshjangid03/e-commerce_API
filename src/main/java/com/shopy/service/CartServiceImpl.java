package com.shopy.service;

import org.springframework.stereotype.Service;

import com.shopy.exception.CartException;
import com.shopy.exception.ProductException;
import com.shopy.model.Cart;

@Service
public class CartServiceImpl implements CartService{

	@Override
	public Cart addCart(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart viewCart(int cartId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart addItemIntoCart(int cartId, int productId) throws CartException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart removeItemFromCart(int cartId, int productId) throws CartException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart increaseQuantity(int cartId, int productId, int quantity) throws CartException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart decreaseQuantity(int cartId, int productId, int quantity) throws CartException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart clearCart(int cartId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart deleteCart(int cartId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

}
