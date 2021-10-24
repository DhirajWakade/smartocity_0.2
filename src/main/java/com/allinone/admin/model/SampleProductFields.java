package com.allinone.admin.model;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.allinone.model.business.BusinessTypes;

public class SampleProductFields {

	private BusinessTypes businessType;
	
	private Field field;
	
	private List<Values> values;
	
	

	public BusinessTypes getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessTypes businessType) {
		this.businessType = businessType;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public List<Values> getValues() {
		return values;
	}

	public void setValues(List<Values> values) {
		this.values = values;
	}
	
}
