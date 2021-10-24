package com.allinone.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="business_user")
@NamedQueries({
    @NamedQuery(name="BusinessmanUser.findByMobileNo",query="SELECT c FROM BusinessmanUser c WHERE c.mobileNo = :mobileNo"),
}) 
public class BusinessmanUser {
	
	@Id
	@Column(name="bmid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bmId;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="businessmanname")
	private String businessUsername;
	
	@Column(name="password")
	private String password;
	
	@Column(name="mobileno")
	private String mobileNo;
	
	@Column(name="emailid")
	private String emailId;
	
	@OneToMany(cascade=CascadeType.MERGE)
	@JsonIgnore
	private Set<BusinessDetails>businessDetails=new HashSet<>();
	
	@Column(name="fcm_id")
	private String FCMId;
	

	public String getFCMId() {
		return FCMId;
	}
	public void setFCMId(String fCMId) {
		FCMId = fCMId;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Long getBmId() {
		return bmId;
	}

	public void setBmId(Long bmId) {
		this.bmId = bmId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBusinessUsername() {
		return businessUsername;
	}

	public void setBusinessUsername(String businessUsername) {
		this.businessUsername = businessUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<BusinessDetails> getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(Set<BusinessDetails> businessDetails) {
		this.businessDetails = businessDetails;
	}
	
	public Boolean addBusinessDetails(BusinessDetails bd)
	{
		return this.businessDetails.add(bd);
	}

}
