package com.lti.cartitemservice.controller;

import java.util.List;
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
import com.lti.cartitemservice.modal.CartItems;
import com.lti.cartitemservice.service.CartItemService;

@RestController
@RequestMapping("/cartitem")
public class CartItemController {

	private static final Logger logger = LoggerFactory.getLogger(CartItemController.class);

	@Autowired
	private CartItemService<CartItems, Long> cartservice;

	//get cart item by UUID ===> http://localhost:9090/cartitem/5
	@GetMapping("/findbyuuid/{uuid}")
	public ResponseEntity<CartItems> getCartItemId(@PathVariable("uuid") String uuid) {
		logger.info("CartItem Controller :: getCartItemId : CartItem UUID ->  " + uuid);
		CartItems cartItems = cartservice.getCartItemByUuid(uuid);
		return new ResponseEntity<CartItems>(cartItems, HttpStatus.OK);
	}

	//fetch All cart item ===> http://localhost:9090/cartitem
	@GetMapping("/findall")
	public ResponseEntity<List<CartItems>> getAllCartItems() {
		logger.info("CartItem Controller :: getAllCartItems  ");
		return ResponseEntity.ok(cartservice.getAllCartItems());
	}

	//add cart item ==> http://localhost:9090/cartitem
	@PostMapping("/add")
	public ResponseEntity<CartItems> addCartItem(@RequestBody CartItems cartItems) {
		logger.info("CartItem Controller :: addCartItem : Request ->  " + cartItems);
		return new ResponseEntity<CartItems>(cartservice.addCardItem(cartItems), HttpStatus.CREATED);
	}

	//update cart item.===> http://localhost:9090/cartitem/5
	@PutMapping("/update")
	public ResponseEntity<CartItems> updateCartItem(@RequestBody CartItems cartItems) {
		logger.info("CartItem Controller :: updateCartItem : Request ->  " + cartItems);
		return new ResponseEntity<CartItems>(cartservice.updateCartItem(cartItems), HttpStatus.OK);
	}

	//delete cart item ===> http://localhost:9090/cartitem/5
	@DeleteMapping("/delete/{uuid}")
	public ResponseEntity<String> deleteCartItem(@PathVariable("uuid") String uuid) {
		logger.info("CartItem Controller :: deleteCartItem : CartItem ID ->  " + uuid);
		cartservice.deleteCartItem(uuid);
		return new ResponseEntity<String>("Cart Item deleted successfully!..", HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteallbycustomeruuid/{customeruuid}")
	public ResponseEntity<Boolean> deleteAllCartItemByCustomerUUID(@PathVariable("customeruuid") String customeruuid) {
		return new ResponseEntity<Boolean>(cartservice.deleteAllCartItemByCustomerUUID(customeruuid), HttpStatus.OK);
	}
	
	@GetMapping("/findbycustomeruuid/{customeruuid}")
	public ResponseEntity<List<CartItems>> getWishlistByCustomer(@PathVariable(value="customeruuid") String customeruuid) {
		List<CartItems> response = cartservice.getAllCartItemsByCustomerUuid(customeruuid);
		return ResponseEntity.ok(response);
	} 
}
