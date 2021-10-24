package com.allinone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allinone.model.AddressDetails;
@Repository
public interface AddressDetailsRepository extends JpaRepository<AddressDetails,Long> {

}
