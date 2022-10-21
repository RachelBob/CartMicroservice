package com.lti.cartitemservice.service;

import java.util.List;

import com.lti.cartitemservice.modal.CartItems;
import com.sun.xml.bind.v2.model.core.ID;

public interface CartItemService<T,ID> {
	  T getCartItemByUuid(String uuid);
	
	  CartItems addCardItem(CartItems cartItems);
    
    CartItems updateCartItem(CartItems cartItems,String uuid);
	
    void deleteCartItem(String uuid);
    
	List<T> getAllCartItems();
	
	
	

}
