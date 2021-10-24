package com.allinone.repository.customer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.allinone.model.customer.HomePageMenu;
@Repository
public interface HomePageMenuRepository extends CrudRepository<HomePageMenu, Long> {

	@Query("SELECT hpmn FROM HomePageMenu hpmn where hpmn.hpMenuStatus=:status")
	public List<HomePageMenu> findMenuByStatus(String status);
	
	@Query("SELECT count(*) FROM HomePageMenu hpmn")
	public Integer findCount();
}
