package com.allinone.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allinone.model.customer.OrderProduct;
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
	
}
