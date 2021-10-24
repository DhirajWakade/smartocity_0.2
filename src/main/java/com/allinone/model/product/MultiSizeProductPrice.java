package com.allinone.model.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_multi_size_product_price")
public class MultiSizeProductPrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="msppid")
	private Long msppId;
	
	@Column(name="quantity")
	private Long quantity;
	
	@OneToOne
	@JoinColumn(name="productsizetypeid")
	private ProductSizeType ProductSizeType;
	
	@Column(name="color")
	private String color;
	
	@Column(name="imp_url")
	private String productImgUrl;
	
	@Column(name="price")
	private Double price;
	
	public MultiSizeProductPrice(){}
	

	public MultiSizeProductPrice(Long quantity, com.allinone.model.product.ProductSizeType productSizeType,
			String color, String productImgUrl) {
		super();
		this.quantity = quantity;
		ProductSizeType = productSizeType;
		this.color = color;
		this.productImgUrl = productImgUrl;
	}
	

	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
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


	public Long getMsppId() {
		return msppId;
	}

	public void setMsppId(Long msppId) {
		this.msppId = msppId;
	}
	

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public ProductSizeType getProductSizeType() {
		return ProductSizeType;
	}

	public void setProductSizeType(ProductSizeType productSizeType) {
		ProductSizeType = productSizeType;
	}

	

}
