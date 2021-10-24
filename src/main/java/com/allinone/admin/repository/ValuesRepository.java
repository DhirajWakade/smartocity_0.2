package com.allinone.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.allinone.admin.model.Values;

@Repository
public interface ValuesRepository extends JpaRepository<Values,Long>{

	@Query(value = "SELECT v FROM Values v WHERE v.vName = :vName")
	public List<Values> findValuesByValueName(String vName);
	
	@Query(value = "SELECT v FROM Values v WHERE v.vKey = :vKey")
	public Values findValuesByKeyName(String vKey);
}
