package com.allinone.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allinone.admin.model.ProductField;

public interface ProductFieldRepository extends JpaRepository<ProductField, Long> {

	@Query(value = "SELECT pf FROM ProductField pf WHERE pf.businessType.businessTypeId = :btid")
	public List<ProductField> findProductFieldByBusinessTypeId(Long btid);
	
}
