package com.allinone.model.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="tbl_business_fields")
public class BusinessFieldsMapper {

	public final static String IS_VISIBLE_YES="Yes";	
	public final static String IS_VISIBLE_NO="No";
	public final static String IS_MANDATORY="Mandatory";
	public final static String IS_OPTIONAL="Optional";
	
	@Id
	@Column(name="field_id")	
	private Long fieldId;
	
	@OneToOne
	@JoinColumn(name="fid")
	private BusinessFieldMaster businessField;
	
	@OneToOne
	@JoinColumn(name="businessTypeId")
	private BusinessTypes businessType;
	
	@Column(name="visible")
	private String isVisible;
	
	@Column(name="mandryorop")
	private String mandaryOrOptional;
	
	
	
	public BusinessFieldsMapper(){}
	public BusinessFieldsMapper(BusinessTypes businesstype,BusinessFieldMaster master,String isVisible,String mandaryOrOptional)
	{
		this.businessField=master;
		this.businessType=businesstype;
		this.isVisible=isVisible;
		this.mandaryOrOptional=mandaryOrOptional;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	

	public BusinessFieldMaster getBusinessField() {
		return businessField;
	}

	public void setBusinessField(BusinessFieldMaster businessField) {
		this.businessField = businessField;
	}

	public BusinessTypes getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessTypes businessType) {
		this.businessType = businessType;
	}

	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}

	public String getMandaryOrOptional() {
		return mandaryOrOptional;
	}

	public void setMandaryOrOptional(String mandaryOrOptional) {
		this.mandaryOrOptional = mandaryOrOptional;
	}
	
	
	
}
