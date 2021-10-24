package com.allinone.model.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.json.JSONPropertyName;

import com.allinone.model.business.BusinessTypes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tbl_product_size_type")
public class ProductSizeType {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="size_type_id")
	private Long sizeTypeId;
	@Column(name="size_type_name")
	private String sizeTypeName;
	@Column(name="size_type_desc")
	private String sizeTypeDesc;
	
	@ManyToMany(mappedBy = "productSizeTypes")
	@JsonIgnore
	private List<BusinessTypes>businessTypes=new ArrayList<BusinessTypes>();
	
	public List<BusinessTypes>addBusinessTypes(BusinessTypes bt)
	{
		this.businessTypes.add(bt);
		return  this.businessTypes;
	}
	
	
	public List<BusinessTypes> getBusinessTypes() {
		return businessTypes;
	}
	public void setBusinessTypes(List<BusinessTypes> businessTypes) {
		this.businessTypes = businessTypes;
	}
	
	public Long getSizeTypeId() {
		return sizeTypeId;
	}
	public void setSizeTypeId(Long sizeTypeId) {
		this.sizeTypeId = sizeTypeId;
	}
	public String getSizeTypeName() {
		return sizeTypeName;
	}
	public void setSizeTypeName(String sizeTypeName) {
		this.sizeTypeName = sizeTypeName;
	}
	public String getSizeTypeDesc() {
		return sizeTypeDesc;
	}
	public void setSizeTypeDesc(String sizeTypeDesc) {
		this.sizeTypeDesc = sizeTypeDesc;
	}
	
	
	
}
