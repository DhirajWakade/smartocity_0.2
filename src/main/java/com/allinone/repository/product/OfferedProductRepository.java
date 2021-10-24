package com.allinone.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.product.OfferedProduct;
@Repository
public interface OfferedProductRepository extends JpaRepository<OfferedProduct, Long> {

	@Query(value = "SELECT op FROM OfferedProduct op WHERE op.status = :status")
	public List<OfferedProduct> findByOfferedProductByStatus(String status);
}
