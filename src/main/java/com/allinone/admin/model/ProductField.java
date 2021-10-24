package com.allinone.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.allinone.model.business.BusinessTypes;
@Entity
@Table(name="tbl_product_fields")
public class ProductField {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pfId;

	@OneToOne
	@JoinColumn(name="businessTypeId")
	private BusinessTypes businessType;
	
	@OneToOne
	@JoinColumn(name="fieldValueId")
	private ProductFieldValues productFieldValues;
	
	
	private String isVisible;
	
	public ProductField(){}
	

	public ProductField(BusinessTypes businessType, ProductFieldValues productFieldValues, String isVisible) {
		super();
		this.businessType = businessType;
		this.productFieldValues = productFieldValues;
		this.isVisible = isVisible;
	}

	public Long getPfId() {
		return pfId;
	}

	public void setPfId(Long pfId) {
		this.pfId = pfId;
	}

	public BusinessTypes getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessTypes businessType) {
		this.businessType = businessType;
	}

	


	public ProductFieldValues getProductFieldValues() {
		return productFieldValues;
	}

	public void setProductFieldValues(ProductFieldValues productFieldValues) {
		this.productFieldValues = productFieldValues;
	}

	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
	
	
	
	
	
}
