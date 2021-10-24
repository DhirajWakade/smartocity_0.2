package com.allinone.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.allinone.model.BusinessDetails;
import com.allinone.model.product.ProductDetails;
import com.allinone.model.product.ProductMaster;
@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long>{

	@Query(value = "SELECT pd FROM ProductDetails pd WHERE pd.businessDetails.businessId = :businessId")
	List<ProductDetails> findByBusinessDetails(Long businessId);
	
	@Query("SELECT pd FROM ProductDetails pd where "
			+ "pd.productMaster.productName like %:desc% "
			+ "or pd.productMaster.productCode like %:desc% "
			+ "or pd.productMaster.productTypes.productType like %:desc% "
			+ "or pd.productMaster.productBrand like %:desc% "
			+ "or pd.productMaster.productColor like %:desc% "
			+ "or pd.productMaster.desciption like %:desc% "
			+ "or pd.productMaster.productMaterial like %:desc%"
			)
	public List<ProductDetails> searchProductDetails(String desc);
	
	@Query(value = "SELECT pd FROM ProductDetails pd WHERE pd.offerStatus = :status")
	List<ProductDetails> findProductDetailsByStatus(String  status);
	
	@Query(value = "SELECT pd FROM ProductDetails pd WHERE pd.productMaster.prodId = :prodId")
	List<ProductDetails> findByProductMasterId(Long prodId);
	
	
}
