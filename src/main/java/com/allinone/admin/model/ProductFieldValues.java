package com.allinone.admin.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.allinone.model.business.BusinessTypes;

@Entity
@Table(name="tbl_field_value")
public class ProductFieldValues {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="fieldValueId")
	private Long fieldValueId;
	
	@OneToOne
	@JoinColumn(name="fId")
	private Field field;
	
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinTable(name = "tbl_field_value_values",
		    joinColumns = @JoinColumn(name = "fieldValueId"),
		    inverseJoinColumns = @JoinColumn(name = "vId"))
	private List<Values> values=new ArrayList<>();
	
	@Column
	private String isFieldComman;
	
	public ProductFieldValues(){}
	
	public ProductFieldValues(Field field, List<Values> values) {
		super();
		this.field = field;
		this.values = values;
	}
	public ProductFieldValues(Field field, List<Values> values,String isFieldComman) {
		super();
		this.field = field;
		this.values = values;
		this.isFieldComman=isFieldComman;
	}
	
	
	public String getIsFieldComman() {
		return isFieldComman;
	}

	public void setIsFieldComman(String isFieldComman) {
		this.isFieldComman = isFieldComman;
	}

	public void addValues(Values v)
	{
		this.values.add(v);
	}
	
	public Long getFieldValueId() {
		return fieldValueId;
	}

	public void setFieldValueId(Long fieldValueId) {
		this.fieldValueId = fieldValueId;
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
