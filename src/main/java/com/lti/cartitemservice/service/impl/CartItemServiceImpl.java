package com.lti.cartitemservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.uuid.Generators;
import com.lti.cartitemservice.modal.CartItems;
import com.lti.cartitemservice.repository.CartItemsRepository;
import com.lti.cartitemservice.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService<CartItems, Long> {

	@Autowired
	private CartItemsRepository repository;

	@Override
	public CartItems getCartItemByUuid(String uuid) {

		return repository.findCartItemByUuid(uuid).get();
		// .orElseThrow(() -> new CartItemNotExistsException("CartItem UUID not
		// Present"+uuid));

	}

	@Override
	public List<CartItems> getAllCartItems() {

		return repository.findAll();
	}

	@Override
	public CartItems addCardItem(CartItems cartItems) {
		UUID uuid = Generators.timeBasedGenerator().generate();
		cartItems.setUuid(uuid.toString());
		return repository.save(cartItems);
	}	
	
	public CartItems updateCartItem(CartItems cartItems, String uuid) {
		CartItems items = repository.findCartItemByUuid(uuid).get();
		// .orElseThrow(() -> new ResourceNotFoundException("CartItem", "Id", id));

		items.setDescription(cartItems.getDescription());
		items.setPrice(cartItems.getPrice());
		items.setProductname(cartItems.getProductname());
		items.setQuantity(cartItems.getQuantity());
		items.setTotalprice(cartItems.getTotalprice());
		repository.save(items);
		return items;

	}

	@Override
	public void deleteCartItem(String uuid) {
	
		repository.findCartItemByUuid(uuid).get();
	}

}
