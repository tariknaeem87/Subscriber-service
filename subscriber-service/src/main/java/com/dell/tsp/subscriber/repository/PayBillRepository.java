package com.dell.tsp.subscriber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dell.tsp.subscriber.entity.PayBillEntity;
import com.dell.tsp.subscriber.entity.SubscriberEntity;

public interface PayBillRepository extends JpaRepository<PayBillEntity, Long> {
	PayBillEntity findByMobileNo( long mobileNo);
}
