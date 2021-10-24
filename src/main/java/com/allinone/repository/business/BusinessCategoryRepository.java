package com.allinone.repository.business;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allinone.model.business.BusinessCategory;

public interface BusinessCategoryRepository extends JpaRepository<BusinessCategory, Long> {

}
