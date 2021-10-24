package com.allinone.notification;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allinone.notification.model.BusinessManNotification;
import com.allinone.notification.model.CustomerNotification;
import com.allinone.notification.repository.BusinessManNotificationRepository;
import com.allinone.notification.repository.CustomerNotificationRepository;
import com.allinone.services.CustomerService;

@Service
public class NotificationService {

	public final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerNotificationRepository customerNotificationRepository;

	@Autowired
	private BusinessManNotificationRepository businessManNotificationRepository;

	public CustomerNotification saveCustomerNotification(CustomerNotification custNotif) {
		String excpMsg = "";
		Boolean needException = false;
		try {
			if (custNotif == null) {
				excpMsg = "CustomerNotification Not null in saveCustomerNotification";
				needException = true;
			}
			if (custNotif.getCustomer() == null) {
				excpMsg = "Customer Not null in saveCustomerNotification";
				needException = true;
			}
			if (custNotif.getNotifText() == null) {
				excpMsg = "Notification text Not null in saveCustomerNotification";
				needException = true;
			}
			if (needException) {
				logger.error(excpMsg);
				throw new NullPointerException(excpMsg);
			}

			custNotif.setStatus(CustomerNotification.NOTIF_STATUS_CREATED);
			custNotif.setReadstatus(CustomerNotification.NOTIF_UN_READ_STATUS);
			CustomerNotification saveNotif = customerNotificationRepository.save(custNotif);
			return saveNotif;
		} catch (Exception e) {
			return null;
		}
	}

	public BusinessManNotification saveBusinessNotification(BusinessManNotification busiNotif) {
		String excpMsg = "";
		Boolean needException = false;
		try {
			if (busiNotif == null) {
				excpMsg = "BusinessManNotification Not null in saveBusinessNotification";
				needException = true;
			}
			if (busiNotif.getBusinessman() == null) {
				excpMsg = "Business Not null in saveBusinessNotification";
				needException = true;
			}
			if (busiNotif.getNotifText() == null) {
				excpMsg = "Notification text Not null in saveBusinessNotification";
				needException = true;
			}
			if (needException) {
				logger.error(excpMsg);
				throw new NullPointerException(excpMsg);
			}

			busiNotif.setStatus(BusinessManNotification.NOTIF_STATUS_CREATED);
			busiNotif.setReadstatus(BusinessManNotification.NOTIF_UN_READ_STATUS);
			BusinessManNotification saveNotif = businessManNotificationRepository.save(busiNotif);
			return saveNotif;
		} catch (Exception e) {
			return null;
		}
	}

	public List<CustomerNotification> findCustomrNotifictioByStatus(String status) {
		return customerNotificationRepository.findNotificationByStatus(status);
	}
	public List<CustomerNotification> findCustomrNotifictioByReadStatus(Long custId,String readstatus) {
		return customerNotificationRepository.findNotificationByReadStatus(custId,readstatus);
	}

	public List<BusinessManNotification> findBusinessNotifictioByStatus(String status) {
		return businessManNotificationRepository.findNotificationByStatus(status);
	}
	public List<BusinessManNotification> findBusinessNotifictioByReadStatus(Long busiId,String status) {
		return businessManNotificationRepository.findNotificationByReadStatus(busiId,status);
	}
	
	public CustomerNotification updateCustomerNotificationStatus(CustomerNotification cn)
	{
		cn.setStatus(CustomerNotification.NOTIF_STATUS_COMPLETED);
		return customerNotificationRepository.saveAndFlush(cn);		
		
	}
	public CustomerNotification updateCustomerNotificationStatus(List<CustomerNotification> cnList)
	{
		for(CustomerNotification cn:cnList)
		{
		cn.setStatus(CustomerNotification.NOTIF_STATUS_COMPLETED);
		 customerNotificationRepository.saveAndFlush(cn);	
		}
		return null;
		
	}
	public BusinessManNotification updateBusinessNotificationStatus(BusinessManNotification cn)
	{
		cn.setStatus(BusinessManNotification.NOTIF_STATUS_COMPLETED);
		return businessManNotificationRepository.saveAndFlush(cn);		
		
	}
	public void updateBusinessNotificationStatus(List<BusinessManNotification> bnList)
	{
		for(BusinessManNotification bn:bnList)
		{
			bn.setStatus(CustomerNotification.NOTIF_STATUS_COMPLETED);
			businessManNotificationRepository.saveAndFlush(bn);	
		}
		
	}

}
