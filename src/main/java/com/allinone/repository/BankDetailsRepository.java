package com.allinone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allinone.model.BankDetails;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Long>{

}
