package com.allinone.model.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.allinone.model.CustomerUser;

@Entity
@Table(name="tbl_cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cart_id")
	private Long cardId;
	
	@OneToOne
	@JoinColumn(name="customer_id")
	private CustomerUser customer;
	
	@Column(name="tot_amt")
	private Double totalAmount;
	
	@OneToMany(cascade = CascadeType.ALL)	
	@JoinTable(name = "tbl_cart_cart_products",
		    joinColumns = @JoinColumn(name = "card_id"),
		    inverseJoinColumns = @JoinColumn(name = "cpid"))
	private List<CartProduct> cardProducts=new ArrayList<>();

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public CustomerUser getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerUser customer) {
		this.customer = customer;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<CartProduct> getCardProducts() {
		return cardProducts;
	}

	public void setCardProducts(List<CartProduct> cardProducts) {
		this.cardProducts = cardProducts;
	}
	
	public void addCardProducts(CartProduct c)
	{
		cardProducts.add(c);
	}
	
	public void removeCartProduct(CartProduct cproduct)
	{
		this.getCardProducts().remove(cproduct);
		this.setTotalAmount(this.getTotalAmount()-cproduct.getTotalAmount());
	}	
	
}
