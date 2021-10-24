package com.allinone.notification;


import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.allinone.notification.model.BusinessManNotification;
import com.allinone.notification.model.CustomerNotification;
import com.allinone.notification.model.Notification;

@Service
public class PushNotificationService {
	

	private String FIRE_BASE_API_URL;
	private String SERVER_KEY;
	
	@Autowired
	private NotificationConfiguration notificationConfiguration;
	
	/*public PushNotificationService() {
		this.FIRE_BASE_API_URL=notificationConfiguration.getFIREBASE_API_URL();
		this.SERVER_KEY=notificationConfiguration.getSERVER_KEY();
	}*/
	
	private String key="key=";
	
	
	
	public ResponseEntity<String> sendNotifaction(Notification notification)
	{
		String FIRE_BASE_API_URL = notificationConfiguration.getFIREBASE_API_URL();
		String SERVER_KEY = notificationConfiguration.getSERVER_KEY();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",key+SERVER_KEY);
		headers.set("Content-Type", "application/json");
		//headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		JSONObject body=new JSONObject();		
		JSONObject data=new JSONObject();
		data.put("title",notification.getTitle());
		data.put("message", notification.getData());
		body.put("notification", data);
		body.put("to",notification.getTo());		
		HttpEntity<String> entity = new HttpEntity<>(body.toString(), headers);
		return restTemplate.exchange(FIRE_BASE_API_URL, HttpMethod.POST, entity, String.class);
	}
	
	public Notification sentCustomerNotifaction(List<CustomerNotification>list)
	{
		for(CustomerNotification c:list)
		{
			Notification n=new Notification("Smartocity App",c.getNotifText(),c.getCustomer().getFCMId());
			sendNotifaction(n);
		}
		return null;
	}
	public Notification sentCustomerNotifaction(CustomerNotification c)
	{
		Notification n=new Notification("Smartocity App",c.getNotifText(),c.getCustomer().getFCMId());
		sendNotifaction(n);
		return null;
	}
	
	public Notification sentBusinessNotifaction(List<BusinessManNotification>list)
	{
		for(BusinessManNotification c:list)
		{
			Notification n=new Notification("Smartocity App",c.getNotifText(),c.getBusinessman().getFCMId());
			if(c.getBusinessman().getFCMId()!=null&&!c.getBusinessman().getFCMId().equals(""))
			{
				sendNotifaction(n);
			}
		}
		return null;
	}
	public Notification sentBusinessNotifaction(BusinessManNotification c)
	{
		Notification n=new Notification("Smartocity App",c.getNotifText(),c.getBusinessman().getFCMId());
		sendNotifaction(n);
		return null;
	}
	
	
	
	
}
