package com.allinone.notification.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.allinone.model.CustomerUser;
import com.allinone.model.DateClass;

@Entity
@Table(name="tbl_customer_notification")
public class CustomerNotification extends DateClass
{
	public static final String NOTIF_STATUS_CREATED="Created";
	public static final String NOTIF_STATUS_PROCESSED="Processed";
	public static final String NOTIF_STATUS_EXPIRED="Expired";
	public static final String NOTIF_STATUS_COMPLETED="Completed";
	public static final String NOTIF_READ_STATUS="Read";
	public static final String NOTIF_UN_READ_STATUS="Not Read";
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long notifId;
	
	@Column(name="text")
	private String notifText;
	
	@OneToOne
	@JoinColumn(name="cust_id")
	private CustomerUser customer;
	
	@Column(name="status")
	private String status;	
	
	@Column(name="read_status")
	private String readstatus;	
	
	@CreationTimestamp
	@Column(name = "insert_time")
	private LocalDateTime insertTime;
	
	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updateTime;	
	
	public CustomerNotification() {}
	

	public CustomerNotification(CustomerUser customer,String notifText) {
		super();
		this.notifText = notifText;
		this.customer = customer;
	}

	public String getReadstatus() {
		return readstatus;
	}

	public void setReadstatus(String readstatus) {
		this.readstatus = readstatus;
	}

	public LocalDateTime getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getNotifId() {
		return notifId;
	}
	public void setNotifId(Long notifId) {
		this.notifId = notifId;
	}
	public String getNotifText() {
		return notifText;
	}
	public void setNotifText(String notifText) {
		this.notifText = notifText;
	}
	public CustomerUser getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerUser customer) {
		this.customer = customer;
	}
	 
}
