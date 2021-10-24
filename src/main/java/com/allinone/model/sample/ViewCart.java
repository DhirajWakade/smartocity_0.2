package com.allinone.model.sample;

import java.util.ArrayList;
import java.util.List;

import com.allinone.model.AddressDetails;
import com.allinone.model.CustomerUser;

public class ViewCart 
{
	private CustomerUser customer;
			
	private Double cartTotalAmount;
	
	private List<ViewCartBusinessDetails>businessDetails=new ArrayList<ViewCartBusinessDetails>();
	
	private AddressDetails orderAddress;	
	
	
	public AddressDetails getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(AddressDetails orderAddress) {
		this.orderAddress = orderAddress;
	}

	public void addViewCartBusinessDetails(ViewCartBusinessDetails d)
	{
		businessDetails.add(d);
	}

	public CustomerUser getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerUser customer) {
		this.customer = customer;
	}

	public Double getCartTotalAmount() {
		return cartTotalAmount;
	}

	public void setCartTotalAmount(Double cartTotalAmount) {
		this.cartTotalAmount = cartTotalAmount;
	}

	public List<ViewCartBusinessDetails> getBusinessDetails() {
		return businessDetails;
	}

	public void setBusinessDetails(List<ViewCartBusinessDetails> businessDetails) {
		this.businessDetails = businessDetails;
	}
	
	
	
	
	

}
