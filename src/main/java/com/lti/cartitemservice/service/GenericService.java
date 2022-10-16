package com.lti.cartitemservice.service;

import java.util.List;

public interface GenericService<T,ID> {
	
	T getCartItemById(ID id);
	
	List<T> getAllCartItems();
	
	List<T> addCartItems(List<T> t);
	
	List<T> updateCartItems(List<T> t);
	
	T deleteCartItem(ID id);
	

}
