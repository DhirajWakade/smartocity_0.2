package com.allinone.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.allinone.model.business.BusinessTypes;
import com.allinone.model.product.ProductDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_business_details")
//@NamedQueries({  
//@NamedQuery(name = "findBusinessByBusinessManId",query = "SELECT b FROM BusinessDetails b where b.businessmanUser.bmId = :businessManId")  
//})  

public class BusinessDetails {

	public static final String CREATED_STATUS="CREATED";
	public static final String AWAITIG_APL_STATUS="AWAITING_FOR_APL";
	public static final String INPROCESS_STATUS="INPROCESS";
	public static final String PROCESSED_STATUS="PROCESSED";
	
	public static final String ACTIVE_STATUS="ACTIVE";
	public static final String DEACTIVE_STATUS="DEACTIVE";
	
	public static final String BUSINESS_REAL="REAL";
	public static final String BUSINESS_TEMP="TEMP";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="business_id")
	private Long businessId;
	
	@Column(name="businessCode")
	private String businessCode;
	
	@Column(name="business_name")
	private String businessName;

	@Column(name="pancard_no")
	private String panCardNo;
	
	@Column(name="pancard_name")
	private String businessNameAaPerPancard;
	
	@OneToOne(cascade=CascadeType.ALL)	
	private BusinessmanUser businessmanUser;  
	
		
	@OneToOne
	@JoinColumn(name ="businessTypeId")
	private BusinessTypes businessType;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name ="addressId")
	private AddressDetails addressDetails;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name ="bankdetails_id")
	private BankDetails bankDetails;
	
	@Column(name="gst_number")
	private String gstNumber;
	
	@Column(name="real_or_temp")
	private String realOrTemp;
	
	@JsonIgnore
	@OneToMany	
	@JoinTable(name = "tbl_business_products",
		    joinColumns = @JoinColumn(name = "business_id"),
		    inverseJoinColumns = @JoinColumn(name = "prod_details_id"))
	private Set<ProductDetails>productDetails=new HashSet<>();
	
	@Column(name="business_email_id")
	private String businessEmailId;
	
	@Column(name="isTACAgreed")
	private Boolean isTermAndConditionAgreed;
	
	@Column(name="min_amt_order")
	private Double minAmtOrderExpected;
	
	@Column(name="cod")
	private String provideCashOnDelivery;
	
	@Column(name="check_img")
	private String checkImageUrl;
	
	@Column(name="FoodLic_img")
	private String foodLicenseImageUrl;
	
	@Column(name="shop_img_url")
	private String shopImgUrl;
	
	@Column(name="pan_img_url")
	private String panCardImgUrl;
	
	@Column(name="address_prof_img_url")
	private String addressProfImgUrl;
	
	private String termAndCondition;
	
	@Column(name="moa_charges")
	private String mOAWithCharges;
	
	@Column(name="deliry_per_km_charges")
	private Double deliveryChargesPerKM;
	
	@Column(name="deliverable_PIN_Code")
	private String deliverablePIN_Code;
	
	@Column(name="free_delivery")
	private String freeDelivery;
	
	@Column(name="fssai_no")
	private String foodLicenseNumber;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(name="fssai_expiry_date")
	private Date foodLicenseNumberExpiryDate;
	
	@Column(name="business_status")
	private String Status;
	
	@Column(name="active_deactive_status")
	private String activeDeactiveStatus;
	
	@Column(name="parent_business_id")
	private Long parentBusinessId;
	
	@CreationTimestamp
    private LocalDateTime createDateTime;
 
    @UpdateTimestamp
    private LocalDateTime updateDateTime;
    
    
    
    public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public Long getParentBusinessId() {
		return parentBusinessId;
	}

	public void setParentBusinessId(Long parentBusinessId) {
		this.parentBusinessId = parentBusinessId;
	}

	public String getRealOrTemp() {
		return realOrTemp;
	}

	public void setRealOrTemp(String realOrTemp) {
		this.realOrTemp = realOrTemp;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	
	public String getActiveDeactiveStatus() {
		return activeDeactiveStatus;
	}

	public void setActiveDeactiveStatus(String activeDeactiveStatus) {
		this.activeDeactiveStatus = activeDeactiveStatus;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getCheckImageUrl() {
		return checkImageUrl;
	}

	public void setCheckImageUrl(String checkImageUrl) {
		this.checkImageUrl = checkImageUrl;
	}

	public String getFoodLicenseImageUrl() {
		return foodLicenseImageUrl;
	}

	public void setFoodLicenseImageUrl(String foodLicenseImageUrl) {
		this.foodLicenseImageUrl = foodLicenseImageUrl;
	}

	public String getPanCardImgUrl() {
		return panCardImgUrl;
	}

	public void setPanCardImgUrl(String panCardImgUrl) {
		this.panCardImgUrl = panCardImgUrl;
	}

	public String getAddressProfImgUrl() {
		return addressProfImgUrl;
	}

	public void setAddressProfImgUrl(String addressProfImgUrl) {
		this.addressProfImgUrl = addressProfImgUrl;
	}

	public String getTermAndCondition() {
		return termAndCondition;
	}

	public void setTermAndCondition(String termAndCondition) {
		this.termAndCondition = termAndCondition;
	}

	public String getmOAWithCharges() {
		return mOAWithCharges;
	}

	public void setmOAWithCharges(String mOAWithCharges) {
		this.mOAWithCharges = mOAWithCharges;
	}

	public Double getDeliveryChargesPerKM() {
		return deliveryChargesPerKM;
	}

	public void setDeliveryChargesPerKM(Double deliveryChargesPerKM) {
		this.deliveryChargesPerKM = deliveryChargesPerKM;
	}

	public String getDeliverablePIN_Code() {
		return deliverablePIN_Code;
	}

	public void setDeliverablePIN_Code(String deliverablePIN_Code) {
		this.deliverablePIN_Code = deliverablePIN_Code;
	}

	public String getFreeDelivery() {
		return freeDelivery;
	}

	public void setFreeDelivery(String freeDelivery) {
		this.freeDelivery = freeDelivery;
	}

	public String getFoodLicenseNumber() {
		return foodLicenseNumber;
	}

	public void setFoodLicenseNumber(String foodLicenseNumber) {
		this.foodLicenseNumber = foodLicenseNumber;
	}

	public Date getFoodLicenseNumberExpiryDate() {
		return foodLicenseNumberExpiryDate;
	}

	public void setFoodLicenseNumberExpiryDate(Date foodLicenseNumberExpiryDate) {
		this.foodLicenseNumberExpiryDate = foodLicenseNumberExpiryDate;
	}

	public Double getMinAmtOrderExpected() {
		return minAmtOrderExpected;
	}

	public void setMinAmtOrderExpected(Double minAmtOrderExpected) {
		this.minAmtOrderExpected = minAmtOrderExpected;
	}

	public String getProvideCashOnDelivery() {
		return provideCashOnDelivery;
	}

	public void setProvideCashOnDelivery(String provideCashOnDelivery) {
		this.provideCashOnDelivery = provideCashOnDelivery;
	}

	public Boolean getIsTermAndConditionAgreed() {
		return isTermAndConditionAgreed;
	}

	public void setIsTermAndConditionAgreed(Boolean isTermAndConditionAgreed) {
		this.isTermAndConditionAgreed = isTermAndConditionAgreed;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getBusinessEmailId() {
		return businessEmailId;
	}

	public void setBusinessEmailId(String businessEmailId) {
		this.businessEmailId = businessEmailId;
	}

	public String getShopImgUrl() {
		return shopImgUrl;
	}

	public void setShopImgUrl(String shopImgUrl) {
		this.shopImgUrl = shopImgUrl;
	}

	public BusinessTypes getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessTypes businessType) {
		this.businessType = businessType;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getPanCardNo() {
		return panCardNo;
	}

	public void setPanCardNo(String panCardNo) {
		this.panCardNo = panCardNo;
	}

	public String getBusinessNameAaPerPancard() {
		return businessNameAaPerPancard;
	}

	public void setBusinessNameAaPerPancard(String businessNameAaPerPancard) {
		this.businessNameAaPerPancard = businessNameAaPerPancard;
	}

	

	public AddressDetails getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(AddressDetails addressDetails) {
		this.addressDetails = addressDetails;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public Set<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Set<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	public BusinessmanUser getBusinessmanUser() {
		return businessmanUser;
	}

	public void setBusinessmanUser(BusinessmanUser businessmanUser) {
		this.businessmanUser = businessmanUser;
	}
	
	public void addProductDetail(ProductDetails pd)
	{
		this.productDetails.add(pd);
	}
	
}
