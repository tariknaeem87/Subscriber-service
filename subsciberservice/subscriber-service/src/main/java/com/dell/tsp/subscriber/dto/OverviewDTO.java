package com.dell.tsp.subscriber.dto;

public class OverviewDTO {
	private long mobileNo;
	private int offerId;
	private int amountPaid;
	private String startDate;
	private String endDate;
	private String offerName;	
	
	public OverviewDTO(long mobileNo, int offerId, int amountPaid, String startDate, String endDate, String offerName) {
		super();
		this.mobileNo = mobileNo;
		this.offerId = offerId;
		this.amountPaid = amountPaid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.offerName = offerName;
	}
	
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
}
