package com.allinone.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.admin.model.ProductFieldValues;
@Repository
public interface ProductFieldValuesRepository extends JpaRepository<ProductFieldValues, Long> {

	@Query(value = "SELECT pfv FROM ProductFieldValues pfv WHERE pfv.field.fName = :fName")
	public ProductFieldValues findProductFieldValueByfName(String fName);
	
}
