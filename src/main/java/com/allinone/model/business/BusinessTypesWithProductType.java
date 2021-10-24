package com.allinone.model.business;

import java.util.List;

import com.allinone.model.product.ProductSizeType;
import com.allinone.model.product.ProductTypes;

public class BusinessTypesWithProductType {
	
	private Long businessTypeId;
	private String businessTypeName;
	private Boolean isGstNoRequired;
	private String businessTypeCode;
	private String isMultiSelection;
	
	private String isProductSearchAllow;
	
	private  List<ProductTypes> productTypes;
	
	private List<ProductSizeType>productSizeTypes;
	
	

	public BusinessTypesWithProductType(Long businessTypeId, String businessTypeName, Boolean isGstNoRequired,
			String businessTypeCode, String isMultiSelection, String isProductSearchAllow,
			List<ProductTypes> productTypes,List<ProductSizeType>productSizeTypes) {
		super();
		this.businessTypeId = businessTypeId;
		this.businessTypeName = businessTypeName;
		this.isGstNoRequired = isGstNoRequired;
		this.businessTypeCode = businessTypeCode;
		this.isMultiSelection = isMultiSelection;
		this.isProductSearchAllow = isProductSearchAllow;
		this.productTypes = productTypes;
		this.productSizeTypes = productSizeTypes;
	}
	

	public List<ProductSizeType> getProductSizeTypes() {
		return productSizeTypes;
	}


	public void setProductSizeTypes(List<ProductSizeType> productSizeTypes) {
		this.productSizeTypes = productSizeTypes;
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

	public String getIsMultiSelection() {
		return isMultiSelection;
	}

	public void setIsMultiSelection(String isMultiSelection) {
		this.isMultiSelection = isMultiSelection;
	}

	public String getIsProductSearchAllow() {
		return isProductSearchAllow;
	}

	public void setIsProductSearchAllow(String isProductSearchAllow) {
		this.isProductSearchAllow = isProductSearchAllow;
	}

	public List<ProductTypes> getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(List<ProductTypes> productTypes) {
		this.productTypes = productTypes;
	}
	
	

}
