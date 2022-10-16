package com.lti.cartitemservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.cartitemservice.modal.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long>{

	//public List<CartItems> findBycartItem(long id);
}
