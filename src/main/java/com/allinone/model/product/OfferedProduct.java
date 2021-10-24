package com.allinone.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.allinone.model.product.ProductDetails;

@Entity
@Table(name="tbl_offered_product")
public class OfferedProduct 
{

	public static String ACTIVE="ACTIVE";
	public static String INACTIVE="INACTIVE";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="offer_product_id")
	private Long offerProductId;
	
	@Column(name="offer_price")
	private Double offerPrice;
	
	@Column(name="mrf_price")
	private Double mrfPrice;
	
	@JoinColumn(name="product_details_id")
	@OneToOne
	private ProductDetails product;
	
	@Column(name="status")
	private String status;


	public Long getOfferProductId() {
		return offerProductId;
	}

	public void setOfferProductId(Long offerProductId) {
		this.offerProductId = offerProductId;
	}

	public Double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(Double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Double getMrfPrice() {
		return mrfPrice;
	}

	public void setMrfPrice(Double mrfPrice) {
		this.mrfPrice = mrfPrice;
	}

	public ProductDetails getProduct() {
		return product;
	}

	public void setProduct(ProductDetails product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
