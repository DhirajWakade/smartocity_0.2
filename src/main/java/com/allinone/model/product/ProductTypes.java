package com.allinone.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.allinone.model.business.BusinessTypes;

@Entity
@Table(name="tbl_product_type")
public class ProductTypes {
	
	@Id
	@Column(name="product_type_id")
	private Long productTypeId;
	@Column(name="product_type_name")
	private String productType;
	
	@OneToOne
	@JoinColumn(name ="business_type_id")
	private BusinessTypes businessTypes;
	
	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public BusinessTypes getBusinessTypes() {
		return businessTypes;
	}
	public void setBusinessTypes(BusinessTypes businessTypes) {
		this.businessTypes = businessTypes;
	}

	
}
