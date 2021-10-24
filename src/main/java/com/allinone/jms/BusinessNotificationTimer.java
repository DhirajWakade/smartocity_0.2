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
import com.allinone.notification.model.BusinessManNotification;
@Component
public class BusinessNotificationTimer {

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private PushNotificationService pushNotificationService;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
	private final Logger log =LoggerFactory.getLogger(this.getClass());
	
		@Scheduled(fixedRate = 15000)
		public void processBusinessNotification() {
			//log.info("******Business Notifaction Trigger****", dateFormat.format(new Date()));
			List<BusinessManNotification>businestnotifList=notificationService.findBusinessNotifictioByStatus(BusinessManNotification.NOTIF_STATUS_CREATED);
			pushNotificationService.sentBusinessNotifaction(businestnotifList);
			notificationService.updateBusinessNotificationStatus(businestnotifList);
		}
}
