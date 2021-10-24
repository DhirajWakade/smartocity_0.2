package com.allinone.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.product.ProductTypes;
@Repository
public interface ProductTypesRepository extends JpaRepository<ProductTypes, Long> {
	@Query("SELECT pt FROM ProductTypes pt WHERE pt.businessTypes.businessTypeId = :busiessId")
	public List<ProductTypes> findAllByBusinessTypeId(Long busiessId);

	@Query("SELECT pt FROM ProductTypes pt WHERE pt.businessTypes.businessTypeId = :businessTypeId")
	public List<ProductTypes> findAllBusinessTypeId(Long businessTypeId);
	
}
