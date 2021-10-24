package com.allinone.model.product;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.allinone.model.BusinessDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="tbl_product_details")
//@NamedQueries({  
//@NamedQuery(name = "ProductDetails.findByProductBusinessId",query = "SELECT * FROM ProductDetails pd WHERE pd.businessDetails.businessId =  :businessId")  
	    
//}) 
public class ProductDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="prod_detail_id")
	private Long productDetailId;
	
	@OneToOne
	@JoinColumn(name ="prod_id")
	private ProductMaster productMaster;
	
	@OneToOne
	//@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name ="business_id")	
	private BusinessDetails businessDetails;
	
	@Column(name="price")
	private Double price;
	
	@Column(name="available_qantity")
	private Long availableQty;
	
	@Column(name="mrp")
	private Double mrpPrice;
	
	@Column
	private Integer taxRate;
	@Column
	private Double finalPrice;
	
	@Column
	private Double dealPrice;
	
	@Column
	private Double gstAmount;
	
	@CreationTimestamp
	@Column(name = "insert_time")
	private LocalDateTime insertTime;
	
	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updateTime;
	
	@Column
	private String hsnCode;
	
	@Column
	private String mostPopular;
	
	@Column(name="offer_price")
	private Double offerPrice;
	
	@Column(name="offer_status")
	private String offerStatus;
	
	
	
	public Double getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(Double mrpPrice) {
		this.mrpPrice = mrpPrice;
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

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}

	public String getMostPopular() {
		return mostPopular;
	}

	public void setMostPopular(String mostPopular) {
		this.mostPopular = mostPopular;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public Integer getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Integer taxRate) {
		this.taxRate = taxRate;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Long getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Long productDetailId) {
		this.productDetailId = productDetailId;
	}

	public ProductMaster getProductMaster() {
		return productMaster;
	}

	public void setProductMaster(ProductMaster productMaster) {
		this.productMaster = productMaster;
	}

	public BusinessDetails getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(BusinessDetails businessDetails) {
		this.businessDetails = businessDetails;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(Long availableQty) {
		this.availableQty = availableQty;
	}

	public LocalDateTime getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}	
	

}
