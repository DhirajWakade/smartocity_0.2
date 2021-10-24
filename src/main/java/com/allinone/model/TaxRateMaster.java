package com.allinone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_tax_rates")
public class TaxRateMaster 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long txId;
	private Long txRate;
	public Long getTxId() {
		return txId;
	}
	public void setTxId(Long txId) {
		this.txId = txId;
	}
	public Long getTxRate() {
		return txRate;
	}
	public void setTxRate(Long txRate) {
		this.txRate = txRate;
	}
	
	

}
