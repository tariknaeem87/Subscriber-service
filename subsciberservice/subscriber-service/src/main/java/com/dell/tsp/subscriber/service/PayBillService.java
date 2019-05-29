package com.dell.tsp.subscriber.service;

import com.dell.tsp.subscriber.entity.PayBillEntity;

public interface PayBillService {
	public PayBillEntity payBill(long subscriberId,long mobileNo, int offerId, int amountPaid, String startDate, String endDate);
}
