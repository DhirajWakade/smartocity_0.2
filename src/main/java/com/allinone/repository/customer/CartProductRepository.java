package com.allinone.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.allinone.model.customer.CartProduct;
@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

	@Modifying
	@Query("DELETE FROM CartProduct cp WHERE cp.cpId in (:cpIdList)")
	void deleteCardProductByIdList(@Param("cpIdList")List<Long> cpIdList);
	
	@Modifying
	@Query("DELETE FROM CartProduct cp WHERE cp.cpId =:cpId")
	void deleteCardProductById(@Param("cpId")Long cpId);
}
