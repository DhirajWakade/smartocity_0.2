package com.allinone.model.product;

import java.util.List;

import javax.persistence.Column;

import com.allinone.model.BusinessDetails;
import com.allinone.model.sample.SampleMultiSizeProductPrice;
import com.allinone.repository.product.ProductSubImage;

public class SampleProduct 
{
	private Long productDetailsId;
	private String brand;
	private String title;
	private String manufacturer_Name;
	private String hscCode;
	private String countryOfOrigin;
	
	private Long availableQuantity;
	
	private Long limitPerOrder;
	private Integer gstRate;
	private String isProductReturnable;
	
	private Integer returnInDays;
	
	private String productDescription;
	private Double MPR;
	private Double dealPrice;
	private Double gstAmount;
	private Double finalPrice;
	private String productColor;
	private Double WeightPerPrduct;
	private String VegNonVeg;
	
	private BusinessDetails businessDetails;
	
	private ProductTypes productTypes;
	
	private String productImageUrl;
	
	private Double productWeight;
	
	private List<SampleMultiSizeProductPrice>multiSizeWithPrice;
	
	private List<ProductSubImage>productSubImg;
	
	
	
	public Long getProductDetailsId() {
		return productDetailsId;
	}
	public void setProductDetailsId(Long productDetailsId) {
		this.productDetailsId = productDetailsId;
	}
	public List<ProductSubImage> getProductSubImg() {
		return productSubImg;
	}
	public void setProductSubImg(List<ProductSubImage> productSubImg) {
		this.productSubImg = productSubImg;
	}
	public List<SampleMultiSizeProductPrice> getMultiSizeWithPrice() {
		return multiSizeWithPrice;
	}
	public void setMultiSizeWithPrice(List<SampleMultiSizeProductPrice> multiSizeWithPrice) {
		this.multiSizeWithPrice = multiSizeWithPrice;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	public Double getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(Double productWeight) {
		this.productWeight = productWeight;
	}
	public ProductTypes getProductTypes() {
		return productTypes;
	}
	public void setProductTypes(ProductTypes productTypes) {
		this.productTypes = productTypes;
	}
	public BusinessDetails getBusinessDetails() {
		return businessDetails;
	}
	public void setBusinessDetails(BusinessDetails businessDetails) {
		this.businessDetails = businessDetails;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getManufacturer_Name() {
		return manufacturer_Name;
	}
	public void setManufacturer_Name(String manufacturer_Name) {
		this.manufacturer_Name = manufacturer_Name;
	}
	public String getHscCode() {
		return hscCode;
	}
	public void setHscCode(String hscCode) {
		this.hscCode = hscCode;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public Long getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public Long getLimitPerOrder() {
		return limitPerOrder;
	}
	public void setLimitPerOrder(Long limitPerOrder) {
		this.limitPerOrder = limitPerOrder;
	}
	public Integer getGstRate() {
		return gstRate;
	}
	public void setGstRate(Integer gstRate) {
		this.gstRate = gstRate;
	}
	public String getIsProductReturnable() {
		return isProductReturnable;
	}
	public void setIsProductReturnable(String isProductReturnable) {
		this.isProductReturnable = isProductReturnable;
	}
	public Integer getReturnInDays() {
		return returnInDays;
	}
	public void setReturnInDays(Integer returnInDays) {
		this.returnInDays = returnInDays;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public Double getMPR() {
		return MPR;
	}
	public void setMPR(Double mPR) {
		MPR = mPR;
	}
	
	public Double getDealPrice() {
		return dealPrice;
	}
	public void setDealPrice(Double dealPrice) {
		this.dealPrice = dealPrice;
	}
	public Double getGstAmount() {
		return gstAmount;
	}
	public void setGstAmount(Double gstAmount) {
		this.gstAmount = gstAmount;
	}
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public String getProductColor() {
		return productColor;
	}
	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}
	public Double getWeightPerPrduct() {
		return WeightPerPrduct;
	}
	public void setWeightPerPrduct(Double weightPerPrduct) {
		WeightPerPrduct = weightPerPrduct;
	}
	public String getVegNonVeg() {
		return VegNonVeg;
	}
	public void setVegNonVeg(String vegNonVeg) {
		VegNonVeg = vegNonVeg;
	}
	
	
	
	

}
