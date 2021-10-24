package com.allinone.repository.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.business.BusinessFieldsMapper;
@Repository
public interface BusinessFieldsRepository extends JpaRepository<BusinessFieldsMapper, Long> 
{
	@Query(value = "SELECT bf FROM BusinessFieldsMapper bf WHERE bf.businessType.businessTypeId = :businessTypeId")
	List<BusinessFieldsMapper> findAllbybusinessType(Long businessTypeId);
	
	@Query(value = "SELECT bf FROM BusinessFieldsMapper bf WHERE bf.businessType.businessTypeId = :businessTypeId AND bf.businessField.fId=:fieldId")
	BusinessFieldsMapper findAllbybusinessTypeAndField(Long businessTypeId,Long fieldId);

	@Query("SELECT count(*) FROM BusinessFieldsMapper pm")
	public Long findCount();
}
