package com.allinone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allinone.model.CustomerUser;

public interface CustomerRepository extends JpaRepository<CustomerUser, Long> {
	
	public CustomerUser findByCustomerUsername(String username);
	
	public CustomerUser findByMobileNo(String mobileNo);

}
