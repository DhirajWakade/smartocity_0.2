package com.allinone.notification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Push 
{
	private String to;

	private String priority;

	private Notification notification;

	@JsonProperty(value = "registration_ids")
	private List<String> registrationIds;
	
	public Push() {

	}
	
	public Push(String to, String priority, Notification notification) {
		this.to = to;
		this.priority = priority;
		this.notification = notification;
	}	

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public List<String> getRegistrationIds() {
		return registrationIds;
	}

	public void setRegistrationIds(List<String> registrationIds) {
		this.registrationIds = registrationIds;
	}
	

}
