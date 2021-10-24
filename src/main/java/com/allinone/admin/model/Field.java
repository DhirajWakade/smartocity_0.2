package com.allinone.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_fields")
public class Field 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fId;
	
	private String fName;
	
	private String fKey;
		
	
	public Field() {}
	public Field(String fName) {
		super();
		this.fName = fName;
		this.fKey=fName.toUpperCase();
	}

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfKey() {
		return fKey;
	}

	public void setfKey(String fKey) {
		this.fKey = fKey;
	}

	
}
