package com.allinone.model;

import java.sql.Date;
import java.util.List;

import com.allinone.model.business.BusinessTypes;

public class SampleBusinessDetails 
{
	private Long businessId;
	private String businessName;
	private String panCard;
	private String nameAsPerPan;
	private String gstNumber;
	private String foodLicenseNumber;
	private String foodLicenseNumberExpiryDate;
	private String addressLine1;
	private String addressLine2;
	private String country;
	private String State;
	private String city;
	private Long pincode;
	private String ifscCode;
	private String accountNo;
	private String confirmAccountNo;
	private String checkImageUrl;
	private String shopImageUrl;
	private String foodLicenseImageUrl;
	private String panCardImageUrl;
	private String addressProfImageUrl;
	private String freeDelivery;
	private Double minimumOrderAmount;
	private String mOAWithCharges;
	private Double deliverPChargesPerKM;
	private List<PinCodeMaster> deliverablePIN_Code;
	private String cashOnDelivery;
	private String emailId;
	private String termAndCondition;
	private BusinessmanUser businessmanUser;  
	
	private BusinessTypes businessType;
	
	
	
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public BusinessmanUser getBusinessmanUser() {
		return businessmanUser;
	}
	public void setBusinessmanUser(BusinessmanUser businessmanUser) {
		this.businessmanUser = businessmanUser;
	}
	public BusinessTypes getBusinessType() {
		return businessType;
	}
	public void setBusinessType(BusinessTypes businessType) {
		this.businessType = businessType;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getPanCard() {
		return panCard;
	}
	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}
	public String getNameAsPerPan() {
		return nameAsPerPan;
	}
	public void setNameAsPerPan(String nameAsPerPan) {
		this.nameAsPerPan = nameAsPerPan;
	}
	public String getGstNumber() {
		return gstNumber;
	}
	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}
	public String getFoodLicenseNumber() {
		return foodLicenseNumber;
	}
	public void setFoodLicenseNumber(String foodLicenseNumber) {
		this.foodLicenseNumber = foodLicenseNumber;
	}
	public String getFoodLicenseNumberExpiryDate() {
		return foodLicenseNumberExpiryDate;
	}
	public void setFoodLicenseNumberExpiryDate(String foodLicenseNumberExpiryDate) {
		this.foodLicenseNumberExpiryDate = foodLicenseNumberExpiryDate;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getConfirmAccountNo() {
		return confirmAccountNo;
	}
	public void setConfirmAccountNo(String confirmAccountNo) {
		this.confirmAccountNo = confirmAccountNo;
	}
	public String getCheckImageUrl() {
		return checkImageUrl;
	}
	public void setCheckImageUrl(String checkImageUrl) {
		this.checkImageUrl = checkImageUrl;
	}
	public String getShopImageUrl() {
		return shopImageUrl;
	}
	public void setShopImageUrl(String shopImageUrl) {
		this.shopImageUrl = shopImageUrl;
	}
	public String getFoodLicenseImageUrl() {
		return foodLicenseImageUrl;
	}
	public void setFoodLicenseImageUrl(String foodLicenseImageUrl) {
		this.foodLicenseImageUrl = foodLicenseImageUrl;
	}
	public String getPanCardImageUrl() {
		return panCardImageUrl;
	}
	public void setPanCardImageUrl(String panCardImageUrl) {
		this.panCardImageUrl = panCardImageUrl;
	}
	public String getAddressProfImageUrl() {
		return addressProfImageUrl;
	}
	public void setAddressProfImageUrl(String addressProfImageUrl) {
		this.addressProfImageUrl = addressProfImageUrl;
	}
	public String getFreeDelivery() {
		return freeDelivery;
	}
	public void setFreeDelivery(String freeDelivery) {
		this.freeDelivery = freeDelivery;
	}
	public Double getMinimumOrderAmount() {
		return minimumOrderAmount;
	}
	public void setMinimumOrderAmount(Double minimumOrderAmount) {
		this.minimumOrderAmount = minimumOrderAmount;
	}
	public String getmOAWithCharges() {
		return mOAWithCharges;
	}
	public void setmOAWithCharges(String mOAWithCharges) {
		this.mOAWithCharges = mOAWithCharges;
	}
	public Double getDeliverPChargesPerKM() {
		return deliverPChargesPerKM;
	}
	public void setDeliverPChargesPerKM(Double deliverPChargesPerKM) {
		this.deliverPChargesPerKM = deliverPChargesPerKM;
	}
	public List<PinCodeMaster> getDeliverablePIN_Code() {
		return deliverablePIN_Code;
	}
	public void setDeliverablePIN_Code(List<PinCodeMaster> deliverablePIN_Code) {
		this.deliverablePIN_Code = deliverablePIN_Code;
	}
	public String getCashOnDelivery() {
		return cashOnDelivery;
	}
	public void setCashOnDelivery(String cashOnDelivery) {
		this.cashOnDelivery = cashOnDelivery;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTermAndCondition() {
		return termAndCondition;
	}
	public void setTermAndCondition(String termAndCondition) {
		this.termAndCondition = termAndCondition;
	}
	
	
	
	

}
