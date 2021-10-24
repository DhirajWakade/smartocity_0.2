package com.allinone.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.customer.MainOrder;
@Repository
public interface MainOrderRepository extends JpaRepository<MainOrder, Long> {

	@Query("SELECT count(*) FROM MainOrder od")
	public Integer findCount();
	
	@Query(value = "SELECT mo FROM MainOrder mo WHERE mo.customer.customerId = :custId")
	public List<MainOrder> findMainOrderByCustID(Long custId);
}
