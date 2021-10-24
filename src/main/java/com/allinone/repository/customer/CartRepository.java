package com.allinone.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.customer.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	@Query(value="SELECT ct FROM Cart ct WHERE ct.customer.customerId = :custid")
	public Cart findCartByCustomerId(Long custid );
	

}
