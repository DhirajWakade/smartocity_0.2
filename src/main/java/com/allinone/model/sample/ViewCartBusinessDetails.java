package com.allinone.model.sample;

import java.util.ArrayList;
import java.util.List;
import com.allinone.model.customer.CartProduct;

public class ViewCartBusinessDetails 
{
	private Long businessId;
	
	private String businessName;
	
	private Double minOrderLimit;
	
	private Double businessTotalAmount;
	
	private List<CartProduct>cartProducts=new ArrayList<CartProduct>();
	
	public void addCartProduct(CartProduct cp)
	{
		cartProducts.add(cp);
	}
	

	public Double getBusinessTotalAmount() {
		return businessTotalAmount;
	}

	public void setBusinessTotalAmount(Double businessTotalAmount) {
		this.businessTotalAmount = businessTotalAmount;
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

	public Double getMinOrderLimit() {
		return minOrderLimit;
	}

	public void setMinOrderLimit(Double minOrderLimit) {
		this.minOrderLimit = minOrderLimit;
	}

	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}
	
	
	
	

}
