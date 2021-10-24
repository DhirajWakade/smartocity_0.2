package com.allinone.model.customer;

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
@Table(name="tbl_cart_product")
public class CartProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cpid")
	private Long cpId;
	
	@OneToOne
	@JoinColumn(name="product_details_id")
	private ProductDetails productDetails;
	
	@Column(name="qantity")
	private Integer quantity;
	
	@Column(name="tot_amt")
	private Double totalAmount;

	public Long getCpId() {
		return cpId;
	}

	public void setCpId(Long cpId) {
		this.cpId = cpId;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
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

	public void setTotalAmount(Double total_amount) {
		this.totalAmount = total_amount;
	}	
	

}
