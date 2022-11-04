package com.lti.cartitemservice.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.uuid.Generators;
import com.lti.cartitemservice.consumer.CustomerDetailsConsumer;
import com.lti.cartitemservice.exception.CartItemNotExistsException;
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
		Optional<CartItems> optionalObj = Optional.ofNullable(repository.findCartItemByUuid(uuid));
		CartItems cartItems = optionalObj
				.orElseThrow(() -> new CartItemNotExistsException("CartItem not found with id:" + uuid));
		return cartItems;
		// return repository.findCartItemByUuid(uuid);
	}

	@Override
	public List<CartItems> getAllCartItems() {
		return repository.findAll();
	}

	@Override
	public CartItems addCardItem(CartItems cartItems) {
		String customerUUID = cartItems.getCustomer_uuid();
		String customerByUUID = customerConsumer.getCustomerByUUID(customerUUID);
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(customerByUUID);
			System.out.println("Entire Book string, " + customerByUUID);
			System.out.println("customer id, " + jsonObject.get("customerId"));
			String id = jsonObject.get("customerId").toString();
			UUID uuid = Generators.timeBasedGenerator().generate();
			cartItems.setUuid(uuid.toString());
			cartItems.setCustomer_id(id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return repository.save(cartItems);
	}

	public CartItems updateCartItem(CartItems cartItems) {
		CartItems items = repository.findCartItemByUuid(cartItems.getUuid());
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
		CartItems cartItemObj = repository.findCartItemByUuid(uuid);
		repository.deleteById(cartItemObj.getCartitem_id());
	}

	@Override
	@Transactional
	public boolean deleteAllCartItemByCustomerUUID(String customerUUID) {
		String custStrObj = customerConsumer.getCustomerByUUID(customerUUID);
		JSONObject custJsonObj;
		boolean isCartDelete = false;
		int isCartDeleted = 0;
		try {
			custJsonObj = new JSONObject(custStrObj);
			isCartDeleted = repository.deleteByCustomer_id(custJsonObj.get("customerId").toString());
			isCartDelete = (isCartDeleted > 0);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return isCartDelete;
	}

	public List<CartItems> getAllCartItemsByCustomerUuid(String customerUUID) {
		String custStrObj = customerConsumer.getCustomerByUUID(customerUUID);
		JSONObject custJsonObj;
		List<CartItems> cartItemList = null;
		try {
			custJsonObj = new JSONObject(custStrObj);
			cartItemList = repository.findByCustomer_id(custJsonObj.get("customerId").toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cartItemList;
	}

}
