package com.dell.tsp.subscriber.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paybill")
public class PayBillEntity {
	
	private long mobileNo;
	private int offerId;
	private int amountPaid;
	private String startDate;
	private String endDate;
	
	@Column(name="amount_paid" , nullable = false)
	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public PayBillEntity() {
		super();
	}

	public PayBillEntity(long mobileNo, int offerId, int amountPaid, String startDate, String endDate) {
		super();
		this.mobileNo = mobileNo;
		this.offerId = offerId;
		this.amountPaid = amountPaid;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Id
	@Column(name="mobile_no" , nullable = false)	
	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name="offer_id" , nullable = false)
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	@Column(name="start_date" , nullable = false)
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name="end_date" , nullable = false)
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
