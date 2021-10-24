package com.allinone.model.business;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.allinone.util.Util;

@Entity
@Table(name="tbl_business_field_mst")
public class BusinessFieldMaster 
{
	@Id
	private Long fId;
	
	private String fieldName;
	
	private String fieldCode;
	
	public BusinessFieldMaster() {}
	public BusinessFieldMaster(String fieldName)
	{
		this.fieldName=fieldName;
		this.fieldCode=Util.toUpperCaseWithSpaceReplace(fieldName);
	}

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String filedCode) {
		this.fieldCode = Util.toUpperCaseWithSpaceReplace(filedCode);
	}
	

	
	

}
