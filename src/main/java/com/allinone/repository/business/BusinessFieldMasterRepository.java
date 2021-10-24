package com.allinone.repository.business;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.model.business.BusinessFieldMaster;
import com.allinone.model.business.BusinessTypes;
@Repository
public interface BusinessFieldMasterRepository extends JpaRepository<BusinessFieldMaster, Long> {
	
	@Query("SELECT count(*) FROM BusinessFieldMaster pm")
	public Integer findCount();
	
	@Query(value="SELECT bfm FROM BusinessFieldMaster bfm WHERE bfm.fieldCode = :code")
	public BusinessFieldMaster findByFieldCode(String code );

}
