package com.allinone.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_values")
public class Values {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="vId")
	private Long vId;
	
	private String vName;
	
	private String vKey;
	
	public Values() {}
	public Values(String vName) {
		super();
		this.vName = vName;
		this.vKey=vName.toUpperCase().replace(" ","_");
	}

	public Long getvId() {
		return vId;
	}

	public void setvId(Long vId) {
		this.vId = vId;
	}

	public String getvName() {
		return vName;
	}

	public void setvName(String vName) {
		this.vName = vName;
	}

	public String getvKey() {
		return vKey;
	}

	public void setvKey(String vKey) {
		this.vKey = vKey;
	}
	
	
	
	

}
