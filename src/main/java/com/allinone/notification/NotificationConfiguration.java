package com.allinone.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:notification.properties")
public class NotificationConfiguration {
	
	//Notification Configaration
	@Value("${notif.config.server.key}")
	private String SERVER_KEY;
	
	@Value("${notif.config.firebase.api.url}")
	private String FIREBASE_API_URL;
	
	//Customer Notification
	@Value("${notif.customer.order.placeorder}")
	private String customerPlaceOrder;
	
	//Business Notification
	@Value("${notif.business.order.receiverorder}")
	private String businessReceiveOrder;
	
		
	public String getCustomerPlaceOrder() {
		return customerPlaceOrder;
	}

	public void setCustomerPlaceOrder(String customerPlaceOrder) {
		this.customerPlaceOrder = customerPlaceOrder;
	}

	public String getBusinessReceiveOrder() {
		return businessReceiveOrder;
	}

	public void setBusinessReceiveOrder(String businessReceiveOrder) {
		this.businessReceiveOrder = businessReceiveOrder;
	}

	public String getSERVER_KEY() {
		return SERVER_KEY;
	}

	public void setSERVER_KEY(String sERVER_KEY) {
		SERVER_KEY = sERVER_KEY;
	}

	public String getFIREBASE_API_URL() {
		return FIREBASE_API_URL;
	}

	public void setFIREBASE_API_URL(String fIREBASE_API_URL) {
		FIREBASE_API_URL = fIREBASE_API_URL;
	}
	
}
