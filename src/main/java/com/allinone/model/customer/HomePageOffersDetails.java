package com.allinone.model.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_offers_details")
public class HomePageOffersDetails {

	public static String OFR_STATUS_ACTIVE="ACTIVE";
	public static String OFR_STATUS_INACTIVE="INACTIVE";
	
	@Id
	@Column(name="offer_number")
	private String offerNumber;
	
	@Column(name="offer_text")
	private String offerText;
	
	@Column(name="offer_img")
	private String offerImg;

	@Column(name="offer_status")
	private String offerStatus;

	
	public HomePageOffersDetails(String offerText, String offerImg) {
		super();
		this.offerText = offerText;
		this.offerImg = offerImg;
		this.offerStatus = OFR_STATUS_ACTIVE;
	}

	public HomePageOffersDetails(String offerNumber, String offerText, String offerImg, String offerStatus) {
		super();
		this.offerNumber = offerNumber;
		this.offerText = offerText;
		this.offerImg = offerImg;
		this.offerStatus = offerStatus;
	}

	public String getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(String offerStatus) {
		this.offerStatus = offerStatus;
	}	

	public String getOfferNumber() {
		return offerNumber;
	}

	public void setOfferNumber(String offerNumber) {
		this.offerNumber = offerNumber;
	}

	public String getOfferText() {
		return offerText;
	}

	public void setOfferText(String offerText) {
		this.offerText = offerText;
	}

	public String getOfferImg() {
		return offerImg;
	}

	public void setOfferImg(String offerImg) {
		this.offerImg = offerImg;
	}
	
}
