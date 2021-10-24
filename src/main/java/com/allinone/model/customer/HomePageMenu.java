package com.allinone.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tbl_home_page_menu")
public class HomePageMenu {
	
	public static String MN_STATUS_ACTIVE="ACTIVE";
	public static String MN_STATUS_INACTIVE="INACTIVE";
	
	@Id
	@Column(name="hp_menu_no")
	private String hpMenuNumber;	
	
	@Column(name="hp_menu_text")
	private String hpMenuText;
	
	@Column(name="hp_menu_img")
	private String hpMenuImgUrl;
	
	@Column(name="hp_menu_status")
	private String hpMenuStatus;	

	public String getHpMenuNumber() {
		return hpMenuNumber;
	}

	public void setHpMenuNumber(String hpMenuNumber) {
		this.hpMenuNumber = hpMenuNumber;
	}

	public String getHpMenuText() {
		return hpMenuText;
	}

	public void setHpMenuText(String hpMenuText) {
		this.hpMenuText = hpMenuText;
	}

	public String getHpMenuImgUrl() {
		return hpMenuImgUrl;
	}

	public void setHpMenuImgUrl(String hpMenuImgUrl) {
		this.hpMenuImgUrl = hpMenuImgUrl;
	}

	public String getHpMenuStatus() {
		return hpMenuStatus;
	}

	public void setHpMenuStatus(String hpMenuStatus) {
		this.hpMenuStatus = hpMenuStatus;
	}

	
}
