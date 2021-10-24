package com.allinone.model.sample;

import com.allinone.model.CustomerUser;
import com.allinone.model.customer.CartProduct;

public class RemoveFromCart {

	private CustomerUser customer;
	
	private CartProduct cartProduct;

	public CustomerUser getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerUser customer) {
		this.customer = customer;
	}

	public CartProduct getCartProduct() {
		return cartProduct;
	}

	public void setCartProduct(CartProduct cartProduct) {
		this.cartProduct = cartProduct;
	}
	
	
}
