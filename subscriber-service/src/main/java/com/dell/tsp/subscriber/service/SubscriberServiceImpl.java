package com.dell.tsp.subscriber.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dell.tsp.subscriber.dto.OfferDTO;
import com.dell.tsp.subscriber.dto.ServiceDTO;
import com.dell.tsp.subscriber.dto.ServiceGroupDTO;
import com.dell.tsp.subscriber.dto.SubscriberDTO;
import com.dell.tsp.subscriber.entity.OfferEntity;
import com.dell.tsp.subscriber.entity.ServiceEntity;
import com.dell.tsp.subscriber.entity.ServiceGroupEntity;
import com.dell.tsp.subscriber.entity.SubscriberEntity;
import com.dell.tsp.subscriber.model.Subscriber;
import com.dell.tsp.subscriber.repository.OfferRepository;
import com.dell.tsp.subscriber.repository.ServiceGroupRepository;
import com.dell.tsp.subscriber.repository.ServiceRepository;
import com.dell.tsp.subscriber.repository.SubscriberRepository;

@Service
public class SubscriberServiceImpl implements SubscriberService{


	private static Logger LOG = LoggerFactory.getLogger(SubscriberServiceImpl.class);
	
	private SubscriberRepository subscriberRepository;
	private SubscriberEntity subscriberEntity;
	
	EntityManager entityManager;
	
//	@Autowired
	JdbcTemplate jdbcTemplate;
	
//	@Autowired
	OfferRepository offerRepository;
	
//	@Autowired
	ServiceRepository serviceRepository;
	
//	@Autowired
	ServiceGroupRepository serviceGroupRepository;

//	@Autowired
	public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
		this.subscriberRepository = subscriberRepository;
	}

	@Autowired


	public ArrayList<SubscriberEntity> getAllSubscribers() {
		return (ArrayList<SubscriberEntity>) subscriberRepository.findAll();
	}

	@Autowired
	public SubscriberServiceImpl(SubscriberRepository subscriberRepository,
			EntityManager entityManager, JdbcTemplate jdbcTemplate, OfferRepository offerRepository, ServiceRepository serviceRepository,
			ServiceGroupRepository serviceGroupRepository) {
		super();
		this.subscriberRepository = subscriberRepository;
		this.entityManager = entityManager;
		this.jdbcTemplate = jdbcTemplate;
		this.offerRepository = offerRepository;
		this.serviceRepository = serviceRepository;
		this.serviceGroupRepository = serviceGroupRepository;
	}
	
	public SubscriberServiceImpl() {}

	public void verifySubscriber(Subscriber subscriber) {
		
	}

	public SubscriberEntity registerSubscriber(Subscriber subscriber) {
		LOG.info("Inside service: registerSubscriber() "+ subscriber.getMobileNo());
		return subscriberRepository.save(convertToSubscriberEntity(subscriber));
	}
	public SubscriberEntity convertToSubscriberEntity(Subscriber subscriber) {
		subscriberEntity = new SubscriberEntity();
		subscriberEntity.setFirstName(subscriber.getFirstName());
		subscriberEntity.setLastName(subscriber.getLastName());
		subscriberEntity.setMobileNo(subscriber.getMobileNo());
		subscriberEntity.setEmail(subscriber.getEmail());
		subscriberEntity.setAddress(subscriber.getAddress());
		subscriberEntity.setAdharNo(subscriber.getAdharNo());
		subscriberEntity.setPassWord(subscriber.getPassword());
		return subscriberEntity;
	}

	public void addOfferData(ArrayList<OfferDTO> offerList) {
		LOG.info("Inside service: addOfferData() ");
		for(OfferDTO offer : offerList) {
			offerRepository.save(new OfferEntity(offer.getOfferId(), offer.getOfferName(), 
					offer.getValidityInDays(), offer.getServiceGroupId(), offer.getPrice()));
		}
	}

	public ServiceEntity addServiceData(ServiceDTO serviceDTO) {
		LOG.info("Inside service: addServiceData() "+ serviceDTO.getServiceName());
			return serviceRepository.save(new ServiceEntity(serviceDTO.getServiceId(),
					serviceDTO.getServiceName(), serviceDTO.getServiceDesc()));		
	}

	public void addServiceGroupData(ArrayList<ServiceGroupDTO> serviceGroupList) {
		LOG.info("Inside service: addServiceGroupData() ");
		for(ServiceGroupDTO service : serviceGroupList) {
			serviceGroupRepository.save(new ServiceGroupEntity(service.getServiceGroupId(),
					service.getServices()));
		}
	}

	public OfferEntity createSingleOffer(OfferDTO offer) {
		LOG.info("Inside service: createSingleOffer() "+ offer.getOfferName());
		return offerRepository.save(new OfferEntity(offer.getOfferId(), offer.getOfferName(), 
				offer.getValidityInDays(), offer.getServiceGroupId(), offer.getPrice()));
	}

	public OfferEntity modifySingleOffer(OfferDTO offer) {
		LOG.info("Inside service: modifySingleOffer() "+ offer.getOfferName());
		return offerRepository.save(new OfferEntity(offer.getOfferId(), offer.getOfferName(), 
				offer.getValidityInDays(), offer.getServiceGroupId(), offer.getPrice()));
	}

	public boolean deleteSingleOffer(long id) {
		try {
			LOG.info("Inside service: deleteSingleOffer() "+ id);
			offerRepository.deleteById(id);
			/*
			 * offerRepository.delete(new OfferEntity(offer.getOfferId(),
			 * offer.getOfferName(), offer.getValidityInDays(), offer.getServiceGroupId(),
			 * offer.getPrice())) ;
			 */
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return false;
		}
	}

	public ServiceEntity modifyService(ServiceDTO serviceDTO) {
		try {
			LOG.info("Inside service: modifyService() "+ serviceDTO.getServiceName());
			return serviceRepository.save(new ServiceEntity(serviceDTO.getServiceId(),
					serviceDTO.getServiceName(), serviceDTO.getServiceDesc()));
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}

	public boolean deleteService(ServiceDTO serviceDTO) {
		try {
			LOG.info("Inside service: deleteService() "+ serviceDTO.getServiceName());
			serviceRepository.delete(new ServiceEntity(serviceDTO.getServiceId(),
					serviceDTO.getServiceName(), serviceDTO.getServiceDesc()));
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			return false;
		}
	}

	@Override
	public ServiceGroupEntity addServiceGroup(ServiceGroupDTO serviceGroupDTO) {
		LOG.info("Inside service: addServiceGroup() "+ serviceGroupDTO.getServiceGroupId());
		return serviceGroupRepository.save(new ServiceGroupEntity(serviceGroupDTO.getServiceGroupId(), serviceGroupDTO.getServices()));
	}

	@Override
	public List<OfferEntity> getAllOffers() {
		LOG.info("Inside service: getAllOffers() ");
		return offerRepository.findAll();
	}
	
	public SubscriberEntity saveSubscriber(SubscriberDTO dto) {
		LOG.info("Inside service: saveSubscriber() "+ dto.getMobileNo());
		SubscriberEntity  entity = null;
		try { 
			  
			SubscriberEntity subscriberEntity = subscriberRepository.findByMobileNo(dto.getMobileNo());
			
			 if(subscriberEntity == null) {
				   entity = subscriberRepository.save(subscriberEntity);
				 return entity;
			 }else if(subscriberEntity.getMobileNo() == dto.getMobileNo()) {
				 entity.setAddress("User Not Found");
				 return entity;
			 }

		}catch(NullPointerException e) {
			LOG.error(e.getMessage());
		}
		return entity;
	}
	
	public SubscriberEntity getSubscriber(long mobileNo) {	
		LOG.info("Inside service: getSubscriber() "+ mobileNo);
		 return subscriberRepository.findByMobileNo(mobileNo);
		
	}
	
	@Override
	public SubscriberEntity modifySubscriberData(SubscriberDTO subscriber) {
		LOG.info("Inside service: modifySubscriberData() "+ subscriber.getMobileNo());
		
		return subscriberRepository.save(new SubscriberEntity
				(subscriber.getSubscriberId(), subscriber.getFirstName(), 
						subscriber.getAdharNo(),subscriber.getAddress(),
						subscriber.getLastName(), subscriber.getEmail(), 
						subscriber.getMobileNo(), subscriber.getPassWord()));

		
	}

	@Override
	public OfferEntity findSingleOffer(long offer) {
		LOG.info("Inside service: findSingleOffer() "+ offer);
		Optional<OfferEntity> offerData = offerRepository.findById(offer);
		return offerData.get();
	} 	
}
