package com.lti.cartitemservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.uuid.Generators;
import com.lti.cartitemservice.consumer.CustomerDetailsConsumer;
import com.lti.cartitemservice.modal.CartItems;
import com.lti.cartitemservice.repository.CartItemsRepository;
import com.lti.cartitemservice.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService<CartItems, Long> {

	@Autowired
	private CartItemsRepository repository;
	
	@Autowired
	private CustomerDetailsConsumer customerConsumer;

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
	public CartItems addCardItem(CartItems cartItems)  {
		String customerUUID=cartItems.getCustomer_uuid();
		String customerByUUID = customerConsumer.getCustomerByUUID(customerUUID);
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(customerByUUID);
			System.out.println("Entire Book string, " + customerByUUID);
			System.out.println("customer id, " + jsonObject.get("customerId"));
			String id= jsonObject.get("customerId").toString();
			UUID uuid = Generators.timeBasedGenerator().generate();
			cartItems.setUuid(uuid.toString());
			cartItems.setCustomer_id(id);
		} catch (JSONException e) {
			
		}
		
		
		return repository.save(cartItems);
	}	
	
	public CartItems updateCartItem(CartItems cartItems) {
		CartItems items = repository.findCartItemByUuid(cartItems.getUuid()).get();
		// .orElseThrow(() -> new ResourceNotFoundException("CartItem", "Id", id));

		items.setDescription(cartItems.getDescription());
		items.setPrice(cartItems.getPrice());
		items.setProductname(cartItems.getProductname());
		items.setQuantity(cartItems.getQuantity());
		items.setTotalprice(cartItems.getTotalprice());
		items.setCategory(cartItems.getCategory());
		repository.save(items);
		return items;

	}

	@Override
	public void deleteCartItem(String uuid) {
	
		repository.findCartItemByUuid(uuid).get();
	}

}
