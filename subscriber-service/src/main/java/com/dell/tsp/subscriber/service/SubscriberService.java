package com.dell.tsp.subscriber.service;

import java.util.ArrayList;
import java.util.List;

import com.dell.tsp.subscriber.dto.OfferDTO;
import com.dell.tsp.subscriber.dto.ServiceDTO;
import com.dell.tsp.subscriber.dto.ServiceGroupDTO;
import com.dell.tsp.subscriber.dto.SubscriberDTO;
import com.dell.tsp.subscriber.entity.OfferEntity;
import com.dell.tsp.subscriber.entity.ServiceEntity;
import com.dell.tsp.subscriber.entity.ServiceGroupEntity;
import com.dell.tsp.subscriber.entity.SubscriberEntity;
import com.dell.tsp.subscriber.model.Subscriber;

public interface SubscriberService {
	public SubscriberEntity saveSubscriber(SubscriberDTO dto);

	public ArrayList<SubscriberEntity> getAllSubscribers();

	public SubscriberEntity getSubscriber(long mobileNo);

	public void verifySubscriber(Subscriber subscriber);

	public SubscriberEntity registerSubscriber(Subscriber subscriber);

	public void addOfferData(ArrayList<OfferDTO> offerList);

	public ServiceEntity addServiceData(ServiceDTO serviceDTO);

	void addServiceGroupData(ArrayList<ServiceGroupDTO> serviceGroupList);

	public OfferEntity createSingleOffer(OfferDTO offer);
	
	public OfferEntity findSingleOffer(long offer);

	public OfferEntity modifySingleOffer(OfferDTO offer);

	public boolean deleteSingleOffer(long offer);

	public ServiceEntity modifyService(ServiceDTO serviceDTO);

	public boolean deleteService(ServiceDTO serviceDTO);

	public ServiceGroupEntity addServiceGroup(ServiceGroupDTO serviceGroupDTO);

	public List<OfferEntity> getAllOffers();
	
	public SubscriberEntity modifySubscriberData(SubscriberDTO subscriber); 
}