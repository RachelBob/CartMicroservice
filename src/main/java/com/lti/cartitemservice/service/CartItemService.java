package com.lti.cartitemservice.service;

import java.util.List;

import com.lti.cartitemservice.modal.CartItems;
import com.sun.xml.bind.v2.model.core.ID;

public interface CartItemService<T,ID> {
    T getCartItemById(long id);
	
    CartItems addCardItem(CartItems cartItems);
    
	CartItems updateCartItem(CartItems cartItems, long id);
	
	void deleteCartItem(ID id);
    
	List<T> getAllCartItems();
	
	
	

}
