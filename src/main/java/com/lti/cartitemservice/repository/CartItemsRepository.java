package com.lti.cartitemservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.lti.cartitemservice.modal.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {

	CartItems findCartItemByUuid(String uuid);

	@Query("from CartItems c where c.customer_id = ?1")
	List<CartItems> findByCustomer_id(String customer_id);

	@Modifying
	@Query("delete from CartItems c where c.customer_id = ?1")
	int deleteByCustomer_id(String customer_id);
}
