package com.allinone.model.customer;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.allinone.model.AddressDetails;
import com.allinone.model.BusinessDetails;
import com.allinone.model.CustomerUser;

import lombok.Data;

@Entity
@Table(name="tbl_order_details")
@Data
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private Long orderId;
	
	@Column(name = "order_code")
	private String orderCode;
	
	@OneToMany
	private List<OrderProduct>orderProducts;
	
	@Column(name = "total_amt")
	private Double totalAmount;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
	private CustomerUser customer;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_details_id")
	private BusinessDetails businessDetail;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
	private AddressDetails address;
	
	@Column(name = "order_status")
	private String orderStatus;
	
	@CreationTimestamp
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;
	

	public BusinessDetails getBusinessDetail() {
		return businessDetail;
	}

	public void setBusinessDetail(BusinessDetails businessDetail) {
		this.businessDetail = businessDetail;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public CustomerUser getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerUser customer) {
		this.customer = customer;
	}

	public AddressDetails getAddress() {
		return address;
	}

	public void setAddress(AddressDetails address) {
		this.address = address;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	
}
