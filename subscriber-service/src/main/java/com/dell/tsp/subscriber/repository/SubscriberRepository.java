package com.dell.tsp.subscriber.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dell.tsp.subscriber.entity.PayBillEntity;
import com.dell.tsp.subscriber.entity.SubscriberEntity;
import com.dell.tsp.subscriber.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {
	
	SubscriberEntity readBySubscriberId(long subscriberId);
	
	SubscriberEntity findByMobileNo( long mobileNo);

}