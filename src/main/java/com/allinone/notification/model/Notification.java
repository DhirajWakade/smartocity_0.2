package com.allinone.notification.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Notification 
{
	private String sound;
	
	private String title;
	
	private String data;
	
	private String to;

	private String priority;

	@JsonProperty(value = "registration_ids")
	private List<String> registrationIds;
	
	public Notification() {
	}

	
	public Notification(String title, String data, String to) {
		super();
		this.title = title;
		this.data = data;
		this.to = to;
	}


	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public List<String> getRegistrationIds() {
		return registrationIds;
	}

	public void setRegistrationIds(List<String> registrationIds) {
		this.registrationIds = registrationIds;
	}

		
	

}
