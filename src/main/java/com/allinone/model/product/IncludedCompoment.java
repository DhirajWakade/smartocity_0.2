package com.allinone.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tbl_product_include_component")
public class IncludedCompoment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="comp_id")
	private Long componentId;
	
	@Column(name="comp_name")
	private String componetName;
	
	//@ManyToOne(cascade=CascadeType.ALL)
    //private ProductMaster productMaster;

	public Long getComponentId() {
		return componentId;
	}

	public void setComponentId(Long componentId) {
		this.componentId = componentId;
	}

	public String getComponetName() {
		return componetName;
	}

	public void setComponetName(String componetName) {
		this.componetName = componetName;
	}
	
	

}
