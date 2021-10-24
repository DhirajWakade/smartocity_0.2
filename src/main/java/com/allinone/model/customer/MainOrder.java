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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.allinone.model.CustomerUser;

@Entity
@Table(name="tbl_main_order")
public class MainOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_main_id")
	private Long mainOrderId;
	
	@Column(name = "order_main_code")
	private String mainOrderCode;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
	private CustomerUser customer;
	
	@Column(name = "order_status")
	private String mainOrderStatus;
	
	@CreationTimestamp
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;
 
	@OneToMany
	private List<OrderDetails>orderDetails;

	public Long getMainOrderId() {
		return mainOrderId;
	}

	public void setMainOrderId(Long mainOrderId) {
		this.mainOrderId = mainOrderId;
	}

	public String getMainOrderCode() {
		return mainOrderCode;
	}

	public void setMainOrderCode(String mainOrderCode) {
		this.mainOrderCode = mainOrderCode;
	}

	public CustomerUser getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerUser customer) {
		this.customer = customer;
	}

	public String getMainOrderStatus() {
		return mainOrderStatus;
	}

	public void setMainOrderStatus(String mainOrderStatus) {
		this.mainOrderStatus = mainOrderStatus;
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

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	} 
	
	public void addOrderdetails(OrderDetails order)
	{
		this.orderDetails.add(order);
	}

}
