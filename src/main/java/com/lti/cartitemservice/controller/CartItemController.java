package com.lti.cartitemservice.controller;

import java.util.List;

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

import com.lti.cartitemservice.modal.CartItems;
import com.lti.cartitemservice.service.CartItemService;

@RestController
@RequestMapping("/cartitem")
public class CartItemController {

	@Autowired
	private CartItemService<CartItems, Long> cartservice;

	// get cart item by id
	@GetMapping("/id/{cartitem_id}")
	public ResponseEntity<CartItems> getCartItem(@PathVariable("cartitem_id") long cartitem_id) {
		return new ResponseEntity<CartItems>(cartservice.getCartItemById(cartitem_id), HttpStatus.OK);
	}

	// fetch All cart item
	@GetMapping
	public ResponseEntity<List<CartItems>> getAllCartItems() {
         
		return ResponseEntity.ok(cartservice.getAllCartItems());

	}

	// add cart item
	@PostMapping
	public ResponseEntity<CartItems> addCardItem(@RequestBody CartItems cartItems) {
		return new ResponseEntity<CartItems>(cartservice.addCardItem(cartItems), HttpStatus.CREATED);
	}

	// update cart item.
	@PutMapping("{cartitem_id}")
	public ResponseEntity<CartItems> updateCartItem(@PathVariable("cartitem_id") long cartitem_id,
			@RequestBody CartItems cartItems) {

		return new ResponseEntity<CartItems>(cartservice.updateCartItem(cartItems, cartitem_id),HttpStatus.OK);
	}

	// delete cart item
	@DeleteMapping("{cartitem_id}")
	public ResponseEntity<String> deleteCartItem(@PathVariable("cartitem_id") Long cartitem_id) {
		
		 cartservice.deleteCartItem(cartitem_id);
		 return new ResponseEntity<String>("Employee deleted successfully!..",HttpStatus.OK);
		
	}


}
