package com.allinone.model.sample;

import com.allinone.model.CustomerUser;
import com.allinone.model.product.ProductDetails;

public class AddToCart 
{
	private CustomerUser customer;
	
	private ProductDetails productDetail;
	
	private Integer quantity;
	
	private Double totalAmount;

	public CustomerUser getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerUser customer) {
		this.customer = customer;
	}

	public ProductDetails getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetails productDetail) {
		this.productDetail = productDetail;
	}
	

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	

}
