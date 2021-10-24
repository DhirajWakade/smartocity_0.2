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

import com.allinone.model.BusinessDetails;
import com.allinone.model.BusinessmanUser;

@Entity
@Table(name="tbl_business_notification")
public class BusinessManNotification {

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
	@JoinColumn(name="bmId")
	private BusinessmanUser businessman;
	
	@OneToOne
	@JoinColumn(name="businessId")
	private BusinessDetails businessDetails;
	
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
	
	public BusinessManNotification() {}	

	public BusinessManNotification(BusinessmanUser businessman,  BusinessDetails businessDetails,String notifText) {
		super();
		this.notifText = notifText;
		this.businessman = businessman;
		this.businessDetails = businessDetails;
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


	public BusinessmanUser getBusinessman() {
		return businessman;
	}

	public void setBusinessman(BusinessmanUser businessman) {
		this.businessman = businessman;
	}

	public BusinessDetails getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(BusinessDetails businessDetails) {
		this.businessDetails = businessDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	
}
