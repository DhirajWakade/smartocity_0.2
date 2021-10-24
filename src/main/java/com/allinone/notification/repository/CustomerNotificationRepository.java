package com.allinone.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allinone.notification.model.CustomerNotification;

public interface CustomerNotificationRepository extends JpaRepository<CustomerNotification, Long> {

	@Query(value = "SELECT bn FROM CustomerNotification bn WHERE bn.customer.customerId = :customerId")
	public List<CustomerNotification> findNotificationByCustonerId(Long customerId);
	
	@Query(value = "SELECT bn FROM CustomerNotification bn WHERE bn.status = :status")
	public List<CustomerNotification> findNotificationByStatus(String status);
	
	@Query(value = "SELECT bn FROM CustomerNotification bn WHERE bn.customer.customerId=:customer_Id and bn.readstatus = :readstatus")
	public List<CustomerNotification> findNotificationByReadStatus(Long customer_Id,String readstatus);
}
