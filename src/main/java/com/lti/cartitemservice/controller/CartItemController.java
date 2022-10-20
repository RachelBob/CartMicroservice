package com.lti.cartitemservice.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.fasterxml.uuid.Generators;
import com.lti.cartitemservice.modal.CartItems;
import com.lti.cartitemservice.service.CartItemService;

@RestController
@RequestMapping("/cartitem")
public class CartItemController {

	private static final Logger logger = LoggerFactory.getLogger(CartItemController.class);

	@Autowired
	private CartItemService<CartItems, Long> cartservice;

	// get cart item by id ===> http://localhost:9090/cartitem/5
	@GetMapping("{cartitem_id}")
	public ResponseEntity<CartItems> getCartItemId(@PathVariable("cartitem_id") long cartitem_id) {
		logger.info("CartItem Controller :: getCartItemId : CartItem ID ->  " + cartitem_id);
		CartItems cartItems = cartservice.getCartItemById(cartitem_id);
		return new ResponseEntity<CartItems>(cartItems, HttpStatus.OK);

		// return ResponseEntity.ok(cartservice.getCartItemById(cartitem_id));

	}
	

	// fetch All cart item ===> http://localhost:9090/cartitem
	@GetMapping
	public ResponseEntity<List<CartItems>> getAllCartItems() {
		logger.info("CartItem Controller :: getAllCartItems  ");
		return ResponseEntity.ok(cartservice.getAllCartItems());

	}

	// add cart item ==> http://localhost:9090/cartitem
	@PostMapping
	public ResponseEntity<CartItems> addCartItem(@RequestBody CartItems cartItems) {

		logger.info("CartItem Controller :: addCartItem : Request ->  " + cartItems);

		// get unique id
		UUID uuid = Generators.timeBasedGenerator().generate();

		// set any field or data for generate UUID
		cartItems.setUuid(uuid.toString());

		cartservice.addCardItem(cartItems);
		
		return new ResponseEntity<CartItems>(HttpStatus.CREATED);
	}

	// update cart item.===> http://localhost:9090/cartitem/5
	@PutMapping("{cartitem_id}")
	public ResponseEntity<CartItems> updateCartItem(@PathVariable("cartitem_id") long cartitem_id,
			@RequestBody CartItems cartItems) {
		logger.info("CartItem Controller :: updateCartItem : Request ->  " + cartItems);
		return new ResponseEntity<CartItems>(cartservice.updateCartItem(cartItems, cartitem_id), HttpStatus.OK);
	}

	// delete cart item ===> http://localhost:9090/cartitem/5
	@DeleteMapping("{cartitem_id}")
	public ResponseEntity<String> deleteCartItem(@PathVariable("cartitem_id") Long cartitem_id) {
		logger.info("CartItem Controller :: deleteCartItem : CartItem ID ->  " + cartitem_id);
		cartservice.deleteCartItem(cartitem_id);
		return new ResponseEntity<String>("Employee deleted successfully!..", HttpStatus.OK);

	}

}

