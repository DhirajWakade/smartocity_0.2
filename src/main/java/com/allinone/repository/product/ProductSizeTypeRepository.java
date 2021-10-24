
package com.allinone.repository.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.product.ProductSizeType;
@Repository
public interface ProductSizeTypeRepository extends JpaRepository<ProductSizeType, Long> {

	@Query(value="SELECT pt FROM ProductSizeType pt join pt.businessTypes bt WHERE bt.businessTypeId = :busiessId")
	List<ProductSizeType> findAllBusinessTypeId(Long busiessId);
}
