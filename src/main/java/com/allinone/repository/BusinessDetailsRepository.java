package com.allinone.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.allinone.model.BusinessDetails;

public interface BusinessDetailsRepository extends JpaRepository<BusinessDetails, Long> {
	
	@Query("SELECT b FROM BusinessDetails b where b.businessmanUser.bmId = :businessManId")
	Set<BusinessDetails>findBusinessByBusinessManId(Long businessManId);

	@Query("SELECT count(*) FROM BusinessDetails bd")
	public Long findCount();
}
