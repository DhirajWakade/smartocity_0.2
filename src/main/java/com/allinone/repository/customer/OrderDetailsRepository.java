package com.allinone.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.customer.MainOrder;
import com.allinone.model.customer.OrderDetails;
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

	@Query("SELECT count(*) FROM OrderDetails od")
	public Integer findCount();
	
	@Query(value = "SELECT od FROM OrderDetails od WHERE od.businessDetail.businessId = :businessDetailsId")
	public List<OrderDetails> findOrderDetailsByBusinessId(Long businessDetailsId);
}
