package com.allinone.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allinone.model.customer.OrderDetails;
import com.allinone.notification.model.BusinessManNotification;

public interface BusinessManNotificationRepository extends JpaRepository<BusinessManNotification, Long> {

	@Query(value = "SELECT bn FROM BusinessManNotification bn WHERE bn.businessDetails.businessId = :businessDetailsId")
	public List<BusinessManNotification> findNotificationByBusinessId(Long businessDetailsId);
	
	@Query(value = "SELECT bn FROM BusinessManNotification bn WHERE bn.status = :status")
	public List<BusinessManNotification> findNotificationByStatus(String status);
	
	@Query(value = "SELECT bn FROM BusinessManNotification bn WHERE bn.businessman.bmId=:businessManId and bn.readstatus = :readstatus")
	public List<BusinessManNotification> findNotificationByReadStatus(Long businessManId,String readstatus);
}
