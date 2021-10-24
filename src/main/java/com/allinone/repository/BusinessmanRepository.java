package com.allinone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allinone.model.BusinessmanUser;
import com.allinone.model.User;

public interface BusinessmanRepository extends JpaRepository<BusinessmanUser, Long> {

	public BusinessmanUser findByBusinessUsername(String businessUsername);
	public BusinessmanUser findByMobileNo(String mobileNo);
	
	
}
