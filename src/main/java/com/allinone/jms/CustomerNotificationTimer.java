package com.allinone.jms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.allinone.notification.NotificationService;
import com.allinone.notification.PushNotificationService;
import com.allinone.notification.model.CustomerNotification;

@Component
public class CustomerNotificationTimer {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private PushNotificationService pushNotificationService;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
	private final Logger log =LoggerFactory.getLogger(this.getClass());
	
		@Scheduled(fixedRate = 15000)
		public void processCustomerNotification() {
			//log.info("****Customer Notifaction Trigger****", dateFormat.format(new Date()));
			List<CustomerNotification>custnotifList=notificationService.findCustomrNotifictioByStatus(CustomerNotification.NOTIF_STATUS_CREATED);
			pushNotificationService.sentCustomerNotifaction(custnotifList);
			notificationService.updateCustomerNotificationStatus(custnotifList);
		}

}
