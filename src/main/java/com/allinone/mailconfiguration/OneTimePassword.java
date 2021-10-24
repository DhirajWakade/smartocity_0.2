package com.allinone.mailconfiguration;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tbl_otp")
@NamedQueries({
    @NamedQuery(name="OneTimePassword.findByEmail",query="SELECT c FROM OneTimePassword c WHERE c.emailId = :emailId"),
}) 
public class OneTimePassword {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "optid")
	private Long optId;
	@Column(name = "opt")
	private String opt;
	@Column(name = "mailid")
	private String emailId;
	@Column(name = "createTime", updatable = false)
	@CreationTimestamp
	private LocalDateTime createTime;
	OneTimePassword(){}
	
	public OneTimePassword(String opt,String emailid){
		this.opt=opt;
		this.emailId=emailid;
	}
	public Long getOptId() {
		return optId;
	}
	public void setOptId(Long optId) {
		this.optId = optId;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}	
	
	

}
