package com.allinone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tbl_address_details")
public class AddressDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="address_id")
	private Long addrId;
	
	@Column(name="address_line_1")
	private String addreLine1;
	
	@Column(name="address_line_2")
	private String addreLine2;
	
	@Column(name="city")
	private String city;
	
	@Column(name="pin_code")
	private Long pinCode;
	
	@Column(name="state")
	private String state;
	
	@Column(name="country")
	private String country;
	

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getAddrId() {
		return addrId;
	}

	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}

	public String getAddreLine1() {
		return addreLine1;
	}

	public void setAddreLine1(String addreLine1) {
		this.addreLine1 = addreLine1;
	}

	public String getAddreLine2() {
		return addreLine2;
	}

	public void setAddreLine2(String addreLine2) {
		this.addreLine2 = addreLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}	
	
	

}
