package com.allinone.repository.product;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.product.ProductMaster;
import com.allinone.model.product.ProductTypes;
@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long>{

	@Query("SELECT pm FROM ProductMaster pm where "
			+ "pm.productName like %:desc% "
			+ "or pm.productCode like %:desc% "
			+ "or pm.productTypes.productType like %:desc% "
			+ "or pm.productBrand like %:desc% "
			+ "or pm.productColor like %:desc% "
			+ "or pm.desciption like %:desc% "
			+ "or pm.productMaterial like %:desc%"
			)
	public List<ProductMaster> searchProduct(String desc);
	
	@Query("SELECT count(*) FROM ProductMaster pm")
	public Integer findCount();
	
	@Query("SELECT pm FROM ProductMaster pm WHERE pm.productTypes.productTypeId = :productTypeId")
	public List<ProductMaster> findAllProductTypeId(Long productTypeId);
}
