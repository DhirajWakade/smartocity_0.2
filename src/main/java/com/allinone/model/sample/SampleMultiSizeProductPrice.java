package com.allinone.model.sample;

import com.allinone.model.product.ProductSizeType;

public class SampleMultiSizeProductPrice 
{
	private String color;
	private String productImgUrl;

	private Double price;
	private Long availableQty;
	
	private ProductSizeType productSize;
	
	public void setProductSize(ProductSizeType productSize) {
		this.productSize = productSize;
	}
	
	
	public ProductSizeType getProductSize() {
		return productSize;
	}


	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getProductImgUrl() {
		return productImgUrl;
	}
	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
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
	

}
