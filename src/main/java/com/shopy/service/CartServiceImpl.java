package com.shopy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopy.exception.CartException;
import com.shopy.exception.CustomerException;
import com.shopy.exception.ProductException;
import com.shopy.model.Cart;
import com.shopy.model.CurrentUserSession;
import com.shopy.model.Customer;
import com.shopy.model.Product;
import com.shopy.repository.CartRepo;
import com.shopy.repository.CustomerRepo;
import com.shopy.repository.ProductRepo;
import com.shopy.repository.UserSessionRepo;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepo cr;
	
	@Autowired
	private CustomerRepo cusRepo;
	
	@Autowired
	private ProductRepo prepo;
	
	@Autowired
	private UserSessionRepo usrRepo;
	
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
	public Cart addCart(Cart cart,String key) throws CustomerException{
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CustomerException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<Customer>cus=cusRepo.findById(cart.getCustomer().getCustomerId());
		if(cus.isEmpty()) {
			throw new CustomerException("customer not found with id "+cart.getCustomer().getCustomerId());
		}
		if(cus.get().getCart()!=null) {
			throw new CustomerException("customer "+cart.getCustomer().getCustomerId()+" already has cart "+cus.get().getCart().getCartId());
		}
		Customer customer=cus.get();
		
		if(customer.getCustomerId()!=currentUser.getUserId())
			throw new CustomerException("user mismatch please try again");
		
		cart.setCustomer(customer);

		return cr.save(cart);
	}

	@Override
	public Cart viewCart(int cartId,String key) throws CartException {
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<Cart>cart=cr.findById(cartId);
		if(cart.isPresent()) {
			
			if(cart.get().getCustomer().getCustomerId()!=currentUser.getUserId())
				throw new CartException("user mismatch please try again");
			
			return cart.get();
		}
		throw new CartException("cart not found with id "+cartId);
	}

	@Override
	public Cart addItemIntoCart(int productId,String key) throws CartException, ProductException {
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Cart cart=cr.findByCustomerId(currentUser.getUserId());
		
//		Optional<Cart>cartOp=cr.findById(cartId);
//		if(cartOp.isEmpty()) {
//			throw new CartException("cart not found with id "+cartId);
//		}
		
		Optional<Product>pro=prepo.findById(productId);
		if(pro.isEmpty()) {
			throw new ProductException("product mot foumd with id "+productId);
		}
		
		Product product=pro.get();
		
//		if(cart.getCustomer().getCustomerId()!=currentUser.getUserId())
//			throw new CartException("user mismatch please try again");

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
	public Cart removeItemFromCart(int productId, String key) throws CartException, ProductException {
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
//		Optional<Cart>cartOp=cr.findById(cartId);
//		if(cartOp.isEmpty()) {
//			throw new CartException("cart not found with id "+cartId);
//		}
		
		Cart cart=cr.findByCustomerId(currentUser.getUserId());
		
//		if(cart.getCustomer().getCustomerId()!=currentUser.getUserId())
//			throw new CartException("user mismatch please try again");

		
		boolean flag=cart.getProducts().removeIf(p-> p.getProductId()==productId);
		
		if(!flag) {
			throw new ProductException("product "+productId+" is not there in cart "+cart.getCartId());
		}
		
		cart.setTotalPrice(cartTotal(cart.getProducts()));
		cart.setTotalItems(cartTotalQuantity(cart.getProducts()));
		
		return cr.save(cart);
	}

	@Override
	public Cart increaseQuantity(int productId, int quantity,String key) throws CartException, ProductException {
		

		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
//		Optional<Cart>cartOp=cr.findById(cartId);
		
//		if(cartOp.isEmpty()) {
//			throw new CartException("cart not found with id "+cartId);
//		}
		
		Optional<Product>pro=prepo.findById(productId);
		if(pro.isEmpty()) {
			throw new ProductException("product mot foumd with id "+productId);
		}
		
		Cart cart=cr.findByCustomerId(currentUser.getUserId());
		
		if(cart.getCustomer().getCustomerId()!=currentUser.getUserId())
			throw new CartException("user mismatch please try again");

		
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
	public Cart decreaseQuantity(int productId, int quantity,String key) throws CartException, ProductException {
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
//		Optional<Cart>cartOp=cr.findById(cartId);
		Optional<Product>pro=prepo.findById(productId);
//		if(cartOp.isEmpty()) {
//			throw new CartException("cart not found with id "+cartId);
//		}
		if(pro.isEmpty()) {
			throw new ProductException("product not found with id "+productId);
		}
		Cart cart=cr.findByCustomerId(currentUser.getUserId());
		
//		if(cart.getCustomer().getCustomerId()!=currentUser.getUserId())
//			throw new CartException("user mismatch please try again");

		
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
	public Cart clearCart(String key) throws CartException {
		
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		
//		Optional<Cart>cartOp=cr.findById(cartId);
//		if(cartOp.isEmpty()) {
//			throw new CartException("cart not found with id "+cartId);
//		}
		Cart cart=cr.findByCustomerId(currentUser.getUserId());
		
//		if(cart.getCustomer().getCustomerId()!=currentUser.getUserId())
//			throw new CartException("user mismatch please try again");

		cart.getProducts().clear();
		cart.setTotalPrice(0);
		cart.setTotalItems(0);
		return cr.save(cart);
	}

	@Override
	public Cart deleteCart(int cartId,String key) throws CartException {
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		Optional<Cart>cartOp=cr.findById(cartId);
		if(cartOp.isEmpty()) {
			throw new CartException("cart not found with id "+cartId);
		}
		if(cartOp.get().getCustomer().getCustomerId()!=currentUser.getUserId())
			throw new CartException("user mismatch please try again");

		cr.delete(cartOp.get());
		return cartOp.get();
	}

	@Override
	public Cart cartByCustomerId(String key) throws CartException {
		List<CurrentUserSession>cUser=usrRepo.findByUuid(key);
		if(cUser.size()==0)
			throw new CartException("you are not logged in please log in");
		
		CurrentUserSession currentUser=cUser.get(0);
		
		return cr.findByCustomerId(currentUser.getUserId());
	}

}
