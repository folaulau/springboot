package com.folaukaveinga.multidbs.expense;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "total_amount_paid")
	private Double totalAmountPaid;

	@Column(name = "merchant_name")
	private String merchantName;

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalAmountPaid() {
		return totalAmountPaid;
	}

	public void setTotalAmountPaid(Double totalAmountPaid) {
		this.totalAmountPaid = totalAmountPaid;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", memberId=" + memberId + ", totalAmount=" + totalAmount + ", totalAmountPaid="
				+ totalAmountPaid + ", merchantName=" + merchantName + "]";
	}

}
