package com.shopy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CartException;
import com.shopy.exception.CustomerException;
import com.shopy.exception.ProductException;
import com.shopy.model.Cart;
import com.shopy.model.Customer;
import com.shopy.model.Product;
import com.shopy.repository.CartRepo;
import com.shopy.repository.CustomerRepo;
import com.shopy.repository.ProductRepo;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepo cr;
	
	@Autowired
	private CustomerRepo cusRepo;
	
	@Autowired
	private ProductRepo prepo;
	
	public int cartTotal(List<Product>list) {
		int total=0;
		for(Product p:list) {
			if(p.getQuantity()!=0) {
				total+=p.getPrice()*p.getQuantity();
			}
		}
		return total;
	}
	
	public int cartTotalQuantity(List<Product>list) {
		int total=0;
		for(Product p:list) {
			if(p.getQuantity()!=0) {
				total+=p.getQuantity();
			}
		}
		return total;
	}

	@Override
	public Cart addCart(Cart cart) throws CustomerException{
		
		Optional<Customer>cus=cusRepo.findById(cart.getCustomer().getCustomerId());
		if(cus.isEmpty()) {
			throw new CustomerException("customer not found with id "+cart.getCustomer().getCustomerId());
		}
		if(cus.get().getCart()!=null) {
			throw new CustomerException("customer "+cart.getCustomer().getCustomerId()+" already has cart "+cus.get().getCart().getCartId());
		}
		Customer customer=cus.get();
		cart.setCustomer(customer);

		return cr.save(cart);
	}

	@Override
	public Cart viewCart(int cartId) throws CartException {
		Optional<Cart>cart=cr.findById(cartId);
		if(cart.isPresent()) {
			return cart.get();
		}
		throw new CartException("cart not found with id "+cartId);
	}

	@Override
	public Cart addItemIntoCart(int cartId, int productId) throws CartException, ProductException {
		Optional<Cart>cartOp=cr.findById(cartId);
		Optional<Product>pro=prepo.findById(productId);
		if(cartOp.isEmpty()) {
			throw new CartException("cart not found with id "+cartId);
		}
		if(pro.isEmpty()) {
			throw new ProductException("product mot foumd with id "+productId);
		}
		Cart cart=cartOp.get();
		Product product=pro.get();

		boolean flag=true;
		for(Product p:cart.getProducts()) {
			if(p.getProductId()==productId) {
				flag=false;
				p.setQuantity(p.getQuantity()+1);
			}
		}
		if(flag) {
			product.setQuantity(1);
			cart.getProducts().add(product);
		}
		
		cart.setTotalPrice(cartTotal(cart.getProducts()));
		cart.setTotalItems(cartTotalQuantity(cart.getProducts()));
		
		return cr.save(cart);
		
	}

	@Override
	public Cart removeItemFromCart(int cartId, int productId) throws CartException, ProductException {
		Optional<Cart>cartOp=cr.findById(cartId);
		if(cartOp.isEmpty()) {
			throw new CartException("cart not found with id "+cartId);
		}
		Cart cart=cartOp.get();
		
		boolean flag=cart.getProducts().removeIf(p-> p.getProductId()==productId);
		
		if(!flag) {
			throw new ProductException("product "+productId+" is not there in cart "+cartId);
		}
		
		cart.setTotalPrice(cartTotal(cart.getProducts()));
		cart.setTotalItems(cartTotalQuantity(cart.getProducts()));
		
		return cr.save(cart);
	}

	@Override
	public Cart increaseQuantity(int cartId, int productId, int quantity) throws CartException, ProductException {
		Optional<Cart>cartOp=cr.findById(cartId);
		Optional<Product>pro=prepo.findById(productId);
		if(cartOp.isEmpty()) {
			throw new CartException("cart not found with id "+cartId);
		}
		if(pro.isEmpty()) {
			throw new ProductException("product mot foumd with id "+productId);
		}
		Cart cart=cartOp.get();
		
		cart.getProducts().forEach(p->{
			if(p.getProductId()==productId) {
				p.setQuantity(p.getQuantity()+quantity);
			}
		});
		cart.setTotalPrice(cartTotal(cart.getProducts()));
		cart.setTotalItems(cartTotalQuantity(cart.getProducts()));
		return cr.save(cart);
	}

	@Override
	public Cart decreaseQuantity(int cartId, int productId, int quantity) throws CartException, ProductException {
		Optional<Cart>cartOp=cr.findById(cartId);
		Optional<Product>pro=prepo.findById(productId);
		if(cartOp.isEmpty()) {
			throw new CartException("cart not found with id "+cartId);
		}
		if(pro.isEmpty()) {
			throw new ProductException("product not found with id "+productId);
		}
		Cart cart=cartOp.get();
		
		cart.getProducts().forEach(p->{
			if(p.getProductId()==productId) {
				p.setQuantity(p.getQuantity()-quantity);
				if(p.getQuantity()<0) {
					p.setQuantity(0);
				}
			}
		});
		cart.setTotalPrice(cartTotal(cart.getProducts()));
		cart.setTotalItems(cartTotalQuantity(cart.getProducts()));
		return cr.save(cart);
	}

	@Override
	public Cart clearCart(int cartId) throws CartException {
		Optional<Cart>cartOp=cr.findById(cartId);
		if(cartOp.isEmpty()) {
			throw new CartException("cart not found with id "+cartId);
		}
		Cart cart=cartOp.get();
		cart.getProducts().clear();
		cart.setTotalPrice(0);
		cart.setTotalItems(0);
		return cr.save(cart);
	}

	@Override
	public Cart deleteCart(int cartId) throws CartException {
		Optional<Cart>cartOp=cr.findById(cartId);
		if(cartOp.isEmpty()) {
			throw new CartException("cart not found with id "+cartId);
		}
		cr.delete(cartOp.get());
		return cartOp.get();
	}

}
