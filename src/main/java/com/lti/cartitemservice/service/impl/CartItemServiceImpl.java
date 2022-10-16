package com.lti.cartitemservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.cartitemservice.exception.ResourceNotFoundException;
import com.lti.cartitemservice.modal.CartItems;
import com.lti.cartitemservice.repository.CartItemsRepository;
import com.lti.cartitemservice.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService<CartItems, Long> {

	@Autowired
	private CartItemsRepository repository;

	@Override
	public CartItems getCartItemById(long id) {
		// return repository.findById(id).get();

		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CartItem", "Id", id));
	}

	@Override
	public List<CartItems> getAllCartItems() {
		
		return repository.findAll();
	}

	@Override
	public CartItems addCardItem(CartItems cartItems) {

		return repository.save(cartItems);
	}

	@Override
	public CartItems updateCartItem(CartItems cartItems, long id) {
		CartItems items = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("CartItem", "Id", id));

		items.setDescription(cartItems.getDescription());
		items.setPrice(cartItems.getPrice());
		items.setProductname(cartItems.getProductname());
		items.setQuantity(cartItems.getQuantity());
		items.setTotalprice(cartItems.getTotalprice());
		repository.save(items);
		return items;
	}

	@Override
	public void deleteCartItem(Long id) {
		// check whether a item is in DB or not.
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CartItem", "Id", id));
		repository.deleteById(id);
	}

}
