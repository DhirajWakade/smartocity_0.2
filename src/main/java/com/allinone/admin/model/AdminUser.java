package com.allinone.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_admin_user")
public class AdminUser 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ad_user_id")
	private Long adUserId;
	@Column(name="ad_user_name")
	private String adUserName;
	@Column
	private String adUserMail;
	@Column
	private String adUserPass;
	@Column
	private String adUserMob;
	public Long getAdUserId() {
		return adUserId;
	}
	public void setAdUserId(Long adUserId) {
		this.adUserId = adUserId;
	}
	public String getAdUserName() {
		return adUserName;
	}
	public void setAdUserName(String adUserName) {
		this.adUserName = adUserName;
	}
	public String getAdUserMail() {
		return adUserMail;
	}
	public void setAdUserMail(String adUserMail) {
		this.adUserMail = adUserMail;
	}
	public String getAdUserPass() {
		return adUserPass;
	}
	public void setAdUserPass(String adUserPass) {
		this.adUserPass = adUserPass;
	}
	public String getAdUserMob() {
		return adUserMob;
	}
	public void setAdUserMob(String adUserMob) {
		this.adUserMob = adUserMob;
	}

}
