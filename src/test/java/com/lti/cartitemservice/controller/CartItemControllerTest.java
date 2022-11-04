package com.lti.cartitemservice.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.lti.cartitemservice.modal.CartItems;
import com.lti.cartitemservice.repository.CartItemsRepository;
import com.lti.cartitemservice.service.CartItemService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CartItemControllerTest {

	@InjectMocks
	CartItemController controller;

	@Mock
	CartItemService<CartItems, Long> service;

	@Mock
	CartItemsRepository repository;

	private MockMvc mockMvc;

	@Test
	public void getCartItemIdTest() {
		when(service.getCartItemByUuid("6b46e7d6-592e-11ed-bcca-d1cb6011a9b6")).thenReturn(createCartItemStub());
		ResponseEntity<CartItems> response = controller.getCartItemId("6b46e7d6-592e-11ed-bcca-d1cb6011a9b6");
		assertEquals(response.getBody().getUuid(), "6b46e7d6-592e-11ed-bcca-d1cb6011a9b6");
	}

	@Test
	public void addCartItemTest() {
		CartItems cartitems = createCartItemStub();
		when(service.addCardItem(cartitems)).thenReturn(cartitems);
		assertEquals(cartitems, service.addCardItem(cartitems));
	}

	@Test
	public void getAllCartItemsTest() {
		List<CartItems> cartitems = createCartItemStub1();
		when(service.getAllCartItems()).thenReturn((List<CartItems>) cartitems);
		assertEquals(service.getAllCartItems(), cartitems);
	}

	@Test
	public void deleteCartItemTest() {
		CartItems cartitems = createCartItemStub();
		when(service.getCartItemByUuid("6b46e7d6-592e-11ed-bcca-d1cb6011a9b6")).thenReturn(cartitems);
		service.deleteCartItem(cartitems.getUuid());
		verify(service).deleteCartItem(cartitems.getUuid());
	}

	@Test
	public void deleteAllCartItemByCustomerUUIDTest() {
		CartItems cartitems = createCartItemStub();
		when(service.deleteAllCartItemByCustomerUUID("7416cf11-592f-11ed-91c8-3d53bacad912")).thenReturn(true);
		ResponseEntity<Boolean> response = controller
				.deleteAllCartItemByCustomerUUID("7416cf11-592f-11ed-91c8-3d53bacad912");
		assertTrue(response.getBody());
	}

	@Test
	public void updateCartItemTest() {
		CartItems cartitems = createCartItemStub();
		cartitems.setCategory("mobile");
		cartitems.setPrice(12000);
		cartitems.setDescription("Readmi 12 Prime description");
		cartitems.setProductname("readmi 12 Prime");
		cartitems.setQuantity(1);
		cartitems.setTotalprice(12000);
		CartItems item = service.updateCartItem(cartitems);

		when(service.updateCartItem(cartitems)).thenReturn(item);
		assertEquals(item, service.updateCartItem(cartitems));
	}

	@Test
	public void getWishlistByCustomerTest() {
		List<CartItems> cartitems = createCartItemStub1();
		when(service.getAllCartItemsByCustomerUuid("7416cf11-592f-11ed-91c8-3d53bacad912"))
				.thenReturn((List<CartItems>) cartitems);
		assertEquals(service.getAllCartItemsByCustomerUuid("7416cf11-592f-11ed-91c8-3d53bacad912"), cartitems);
	}

	private CartItems createCartItemStub() {
		CartItems cartItems = new CartItems(1L, "Readmi 14 Prime", "Redmi 14 description", 1, 16000, 16000,
				"6b46e7d6-592e-11ed-bcca-d1cb6011a9b6", "mobile", "1", "7416cf11-592f-11ed-91c8-3d53bacad912");
		return cartItems;

	}

	private List<CartItems> createCartItemStub1() {
		List<CartItems> cartItems1 = Arrays.asList(
				new CartItems(1L, "Readmi 14 Prime", "Redmi 14 description", 1, 16000, 16000,
						"6b46e7d6-592e-11ed-bcca-d1cb6011a9b6", "mobile", "1", "7416cf11-592f-11ed-91c8-3d53bacad912"),
				new CartItems(2L, "Readmi 14 Prime", "Redmi 14 description", 1, 16000, 16000,
						"6b46e7d6-592e-11ed-bcca-d1cb6011a9b6", "mobile", "1", "7416cf11-592f-11ed-91c8-3d53bacad912"));
		return cartItems1;

	}
}
