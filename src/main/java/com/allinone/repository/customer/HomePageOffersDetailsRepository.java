package com.allinone.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.allinone.model.customer.HomePageOffersDetails;
@Repository
public interface HomePageOffersDetailsRepository extends CrudRepository<HomePageOffersDetails, Long> {
	
	@Query("SELECT od FROM HomePageOffersDetails od where od.offerStatus=:status")
	public List<HomePageOffersDetails> findofferDetailsByStatus(String status);
	
	@Query("SELECT count(*) FROM HomePageOffersDetails od")
	public Integer findCount();
}
