package com.dell.tsp.subscriber.dto;

public class PayBillOfferDTO {
	private long mobileNo;
	private int offerId;
    private String offerName;
    private int validityInDays;
    private int serviceGroupId;
    private int amountPaid;
	
	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public int getValidityInDays() {
		return validityInDays;
	}

	public void setValidityInDays(int validityInDays) {
		this.validityInDays = validityInDays;
	}

	public int getServiceGroupId() {
		return serviceGroupId;
	}

	public void setServiceGroupId(int serviceGroupId) {
		this.serviceGroupId = serviceGroupId;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
}
