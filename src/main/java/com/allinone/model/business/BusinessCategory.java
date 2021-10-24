package com.allinone.model.business;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_business_cat")
public class BusinessCategory 
{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bcid")
	private Long bcId;
	@Column(name="bcname")
	private String bcName;
	@Column(name="bccode")
	private String bcCode;
	
	@OneToMany
	private List<BusinessTypes>businessType;
	
	
	public List<BusinessTypes> getBusinessType() {
		return businessType;
	}
	public void setBusinessType(List<BusinessTypes> businessType) {
		this.businessType = businessType;
	}
	public Long getBcId() {
		return bcId;
	}
	public void setBcId(Long bcId) {
		this.bcId = bcId;
	}
	public String getBcName() {
		return bcName;
	}
	public void setBcName(String bcName) {
		this.bcName = bcName;
	}
	public String getBcCode() {
		return bcCode;
	}
	public void setBcCode(String bcCode) {
		this.bcCode = bcCode;
	}
	
}
