package com.allinone.repository.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.business.BusinessTypes;
@Repository
public interface BusinessTypesRepository extends JpaRepository<BusinessTypes, Long> {

	@Query(value="SELECT bt FROM BusinessTypes bt WHERE bt.topBusinessStatus = :status")
	public List<BusinessTypes>findTopBusinessTypeByStatus(String status );
	
	
}
