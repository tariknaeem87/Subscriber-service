package com.dell.tsp.subscriber.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.dell.tsp.subscriber.entity.SubscriberEntity;
import com.dell.tsp.subscriber.repository.SubscriberRepository;

@Repository
public class DataDaoImpl {
	
	@Autowired
	SubscriberRepository subscriberRepository;
	
	
	 public String findByMobileNo(long mobileNo) {
		 try {
			 SubscriberEntity entity = subscriberRepository.findByMobileNo(mobileNo);

			 if(entity == null) {
				 return "User not found";
			 }else {
				 return  entity.getPassWord();
			 }

		 }catch(EmptyResultDataAccessException e) {
			 return "User not found";
		 }
	 }
}
