package com.allinone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_bank_details")
public class BankDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="bankdetails_id")
	private Long bdId;
	
	@Column(name="bank_name")
	private String bankName;
		
	@Column(name="account_no")
	private String accountNo;
	
	@Column(name="ifsc_code")
	private String ifscCode;
	
	@Column(name="chequeOrPassbook")
	private String chequeOrPassbookImgUrl;
	
	
	public String getChequeOrPassbookImgUrl() {
		return chequeOrPassbookImgUrl;
	}

	public void setChequeOrPassbookImgUrl(String chequeOrPassbookImgUrl) {
		this.chequeOrPassbookImgUrl = chequeOrPassbookImgUrl;
	}

	public Long getBdId() {
		return bdId;
	}

	public void setBdId(Long bdId) {
		this.bdId = bdId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	

}
