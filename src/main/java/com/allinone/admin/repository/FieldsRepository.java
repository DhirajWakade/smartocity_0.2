package com.allinone.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allinone.admin.model.Field;

public interface FieldsRepository extends JpaRepository<Field, Long> {

	@Query(value = "SELECT f FROM Field f WHERE f.fName = :fName")
	public List<Field> findFieldByFieldName(String fName);
	
	@Query(value = "SELECT f FROM Field f WHERE f.fKey = :fKey")
	public Field findFieldByKeyName(String fKey);
}
