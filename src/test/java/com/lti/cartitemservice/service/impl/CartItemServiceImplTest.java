package com.lti.cartitemservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.lti.cartitemservice.modal.CartItems;
import com.lti.cartitemservice.repository.CartItemsRepository;

@SpringBootTest
public class CartItemServiceImplTest {
	
	@InjectMocks
	CartItemServiceImpl CartItemsServiceImpl;
	
	@Mock
     CartItemsRepository repository;
	
	@Test
	public void getCartItemById() {
		when(repository.findById(1L)).thenReturn(createCartItemStub());
		
		//CartItems cartItems= CartItemsServiceImpl.getCartItemById(1L);
		//assertEquals(cartItems.getProductname(), "decode");
	}
	
	private Optional<CartItems> createCartItemStub(){
        
       // CartItems cartitem = CartItems.builder().id(1L).name("decode").build();
       // return Optional.of(cartitem);
		return null;
        
    }

}
