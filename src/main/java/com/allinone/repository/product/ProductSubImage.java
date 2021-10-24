package com.allinone.repository.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tbl_product_sub_img")

public class ProductSubImage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="sub_img_id")
	private Long subImgId;
	
	@Column(name="sub_img_url")
	private String subImgUrl;

	public Long getSubImgId() {
		return subImgId;
	}

	public void setSubImgId(Long subImgId) {
		this.subImgId = subImgId;
	}

	public String getSubImgUrl() {
		return subImgUrl;
	}

	public void setSubImgUrl(String subImgUrl) {
		this.subImgUrl = subImgUrl;
	}
	
	

}
