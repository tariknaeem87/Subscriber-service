package com.dell.tsp.subscriber.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dell.tsp.subscriber.entity.PayBillEntity;
import com.dell.tsp.subscriber.entity.SubscriberEntity;
import com.dell.tsp.subscriber.repository.PayBillRepository;
import com.dell.tsp.subscriber.repository.SubscriberRepository;

@Service
public class PayBillServiceImpl implements PayBillService {

	private static final Logger log = LoggerFactory.getLogger(PayBillServiceImpl.class);
	@Autowired
	PayBillRepository payBillRepository;

	@Autowired
	private CustomMessageSender customMessageSender;

	@Autowired
	private SubscriberRepository subscriberRepository;

	public PayBillEntity payBill(long subscriberId, long mobileNo, int offerId, int amountPaid, String startDate,
			String endDate) {
		log.info("Inside Service: payBill()");
		SubscriberEntity subscriber;
		try {
			subscriber = subscriberRepository.readBySubscriberId(subscriberId);
			customMessageSender.sendMessage(new CustomMessage( subscriber.getEmail(),"tsp@notification.com",
					Integer.parseInt(subscriberId + ""), "", "", "Bill Payment", "Payment made successfully",
					LocalDateTime.now().toString()));
		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}
		PayBillEntity entity = payBillRepository
				.save(new PayBillEntity(mobileNo, offerId, amountPaid, startDate, endDate));
		return entity;
	}

}
