package com.allinone.model;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="customer")
@NamedQueries({
    @NamedQuery(name="CustomerUser.findByMobileNo",query="SELECT c FROM CustomerUser c WHERE c.mobileNo = :mobileNo"),
}) 
public class CustomerUser {
	
	@Id
	@Column(name="custid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="custusername")
	private String customerUsername;
	
	@Column(name="password")
	private String password;
	
	@Column(name="mobileno")
	private String mobileNo;
	
	@Column(name="emailid")
	private String emailId;
	
	
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinTable(name = "tbl_customer_address",
		    joinColumns = @JoinColumn(name = "addressId"),
		    inverseJoinColumns = @JoinColumn(name = "customerId"))
	private List<AddressDetails> addressDetails;
	
	@Column(name="fcm_id")
	private String FCMId;
	

	public String getFCMId() {
		return FCMId;
	}
	public void setFCMId(String fCMId) {
		FCMId = fCMId;
	}
	public void addAddress(AddressDetails ad)
	{
		this.addressDetails.add(ad);
	}	
	public List<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetails> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public Long getCustomerId() {
		return customerId;
	}	

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
		
	

}
