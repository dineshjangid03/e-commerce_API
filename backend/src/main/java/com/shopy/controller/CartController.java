package com.shopy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopy.exception.CartException;
import com.shopy.exception.CustomerException;
import com.shopy.exception.ProductException;
import com.shopy.model.Cart;
import com.shopy.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cs;
	
	@GetMapping("/view/{uuidKey}")
	public ResponseEntity<Cart> viewCart(@PathVariable("uuidKey") String key) throws CartException {
		Cart c=cs.cartByCustomerId(key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

//	@PostMapping("/add/{uuidKey}")
//	public ResponseEntity<Cart> addCart(@RequestBody Cart cart, @PathVariable("uuidKey") String key) throws CustomerException{
//		Cart c=cs.addCart(cart,key);
//		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
//	}

//	@GetMapping("/view/{id}/{uuidKey}")
//	public ResponseEntity<Cart> viewCart(@PathVariable("id") int cartId, @PathVariable("uuidKey") String key) throws CartException {
//		Cart c=cs.viewCart(cartId,key);
//		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
//	}

	@PutMapping("/addItemIntoCart/{productId}/{uuidKey}")
	public ResponseEntity<Cart> addItemIntoCart(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.addItemIntoCart(productId,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/removeItemFromCart/{productId}/{uuidKey}")
	public ResponseEntity<Cart> removeItemFromCart(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.removeItemFromCart(productId,key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/increaseQuantity/{productId}/{uuidKey}")
	public ResponseEntity<Cart> increaseQuantity(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.increaseQuantity(productId, key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/decreaseQuantity/{productId}/{uuidKey}")
	public ResponseEntity<Cart> decreaseQuantity(@PathVariable("productId") int productId, @PathVariable("uuidKey") String key) throws CartException, ProductException {
		Cart c=cs.decreaseQuantity(productId, key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/clearCart/{uuidKey}")
	public ResponseEntity<Cart> clearCart(@PathVariable("uuidKey") String key) throws CartException {
		Cart c=cs.clearCart(key);
		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
	}

//	@DeleteMapping("/deleteCart/{cartId}/{uuidKey}")
//	public ResponseEntity<Cart> deleteCart(@PathVariable("cartId") int cartId, @PathVariable("uuidKey") String key) throws CartException {
//		Cart c=cs.deleteCart(cartId,key);
//		return new ResponseEntity<Cart>(c,HttpStatus.ACCEPTED);
//	}


}
