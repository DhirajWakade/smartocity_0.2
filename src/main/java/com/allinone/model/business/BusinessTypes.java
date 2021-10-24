package com.allinone.model.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.allinone.model.product.ProductSizeType;
import com.allinone.model.product.ProductTypes;

import lombok.Setter;

@Entity
@Table(name="tbl_business_type")
@Setter
public class BusinessTypes {

	@Id
	@Column(name="businessTypeId")
	private Long businessTypeId;
	
	@Column(name="businessTypeName")
	private String businessTypeName;
	
	@Column(name="IsGstNoRequired")
	private Boolean isGstNoRequired;
	
	@Column(name="businessTypeCode")
	private String businessTypeCode;
	
	@Column(name="isMultiSelection")
	private String isMultiSelection;
	
	@Column(name="isProductSearchAllow")
	private String isProductSearchAllow;
	
	@Column
	private String isBusinessManRequired;
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "tbl_business_type_product_size_type", 
        joinColumns = { @JoinColumn(name = "business_type_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "product_size_type_id") }
    )
	private List<ProductSizeType>productSizeTypes=new ArrayList<ProductSizeType>();
	
	@Transient
	private  List<ProductTypes> productTypes;	
	
	@OneToOne
	private BusinessCategory businessCat;
	
	@Column(name = "top_buisness_serialNo")
	private Integer topBusinessSerialNo;
	
	@Column(name = "top_buisness_Name")
	private String topBusinessName;
	
	@Column(name = "top_buisness_status")
	private String topBusinessStatus;	
	
	@Column(name = "top_buisness_icon_url")
	private String topBusinessIconUrl;

	
	public List<ProductSizeType>addProductSizeType(ProductSizeType e)
	{
		this.productSizeTypes.add(e);
		return this.productSizeTypes;
	}
	
	
	public BusinessCategory getBusinessCat() {
		return businessCat;
	}

	public void setBusinessCat(BusinessCategory businessCat) {
		this.businessCat = businessCat;
	}


	public String getIsBusinessManRequired() {
		return isBusinessManRequired;
	}


	public void setIsBusinessManRequired(String isBusinessManRequired) {
		this.isBusinessManRequired = isBusinessManRequired;
	}


	public List<ProductSizeType> getProductSizeTypes() {
		return productSizeTypes;
	}
	public void setProductSizeTypes(List<ProductSizeType> productSizeTypes) {
		this.productSizeTypes = productSizeTypes;
	}
	public List<ProductTypes> getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(List<ProductTypes> productTypes) {
		this.productTypes = productTypes;
	}
	public String getIsProductSearchAllow() {
		return isProductSearchAllow;
	}
	public void setIsProductSearchAllow(String isProductSearchAllow) {
		this.isProductSearchAllow = isProductSearchAllow;
	}
	public String getIsMultiSelection() {
		return isMultiSelection;
	}
	public void setIsMultiSelection(String isMultiSelection) {
		this.isMultiSelection = isMultiSelection;
	}
	public Long getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public String getBusinessTypeName() {
		return businessTypeName;
	}
	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}
	
	public Boolean getIsGstNoRequired() {
		return isGstNoRequired;
	}
	public void setIsGstNoRequired(Boolean isGstNoRequired) {
		this.isGstNoRequired = isGstNoRequired;
	}
	public String getBusinessTypeCode() {
		return businessTypeCode;
	}
	public void setBusinessTypeCode(String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	public Integer getTopBusinessSerialNo() {
		return topBusinessSerialNo;
	}

	public void setTopBusinessSerialNo(Integer topBusinessSerialNo) {
		this.topBusinessSerialNo = topBusinessSerialNo;
	}


	public String getTopBusinessName() {
		return topBusinessName;
	}


	public void setTopBusinessName(String topBusinessName) {
		this.topBusinessName = topBusinessName;
	}


	public String getTopBusinessStatus() {
		return topBusinessStatus;
	}


	public void setTopBusinessStatus(String topBusinessStatus) {
		this.topBusinessStatus = topBusinessStatus;
	}


	public String getTopBusinessIconUrl() {
		return topBusinessIconUrl;
	}


	public void setTopBusinessIconUrl(String topBusinessIconUrl) {
		this.topBusinessIconUrl = topBusinessIconUrl;
	}
	
	
}
