package com.shopy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopy.exception.CartException;
import com.shopy.exception.ProductException;
import com.shopy.model.Cart;
import com.shopy.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cs;

	public Cart addCart(Cart cart) {
		return null;
	}

	public Cart viewCart(int cartId) throws CartException {
		return null;
	}

	public Cart addItemIntoCart(int cartId, int productId) throws CartException, ProductException {
		return null;
	}

	public Cart removeItemFromCart(int cartId, int productId) throws CartException, ProductException {
		return null;
	}

	public Cart increaseQuantity(int cartId, int productId, int quantity) throws CartException, ProductException {
		return null;
	}

	public Cart decreaseQuantity(int cartId, int productId, int quantity) throws CartException, ProductException {
		return null;
	}

	public Cart clearCart(int cartId) throws CartException {
		return null;
	}

	public Cart deleteCart(int cartId) throws CartException {
		return null;
	}


}
