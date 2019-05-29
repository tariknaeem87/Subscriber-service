package com.dell.tsp.subscriber.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dell.tsp.subscriber.config.YAMLConfig;
import com.dell.tsp.subscriber.dto.OfferDTO;
import com.dell.tsp.subscriber.dto.OverviewDTO;
import com.dell.tsp.subscriber.dto.PayBillOfferDTO;
import com.dell.tsp.subscriber.dto.ServiceDTO;
import com.dell.tsp.subscriber.dto.ServiceGroupDTO;
import com.dell.tsp.subscriber.dto.SubscriberDTO;
import com.dell.tsp.subscriber.entity.OfferEntity;
import com.dell.tsp.subscriber.entity.PayBillEntity;
import com.dell.tsp.subscriber.entity.ServiceEntity;
import com.dell.tsp.subscriber.entity.ServiceGroupEntity;
import com.dell.tsp.subscriber.entity.SubscriberEntity;
import com.dell.tsp.subscriber.model.Subscriber;
import com.dell.tsp.subscriber.repository.OfferRepository;
import com.dell.tsp.subscriber.repository.PayBillRepository;
import com.dell.tsp.subscriber.repository.SubscriberRepository;
import com.dell.tsp.subscriber.service.LoginService;
import com.dell.tsp.subscriber.service.PayBillService;
import com.dell.tsp.subscriber.service.SubscriberService;

@RestController
@RequestMapping({ "/" })
/* @RefreshScope */
public class SubscriberBaseController {

	private static Logger LOG = LoggerFactory.getLogger(SubscriberBaseController.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private PayBillService payBillService;

	@Autowired
	private SubscriberService subscriberService;

	@Autowired
	private SubscriberRepository subscriberRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PayBillRepository payBillRepository;
	
	@Autowired
	private OfferRepository offerRepository;


	@Autowired
	private YAMLConfig myConfig;
	
	@Value("${adminBaseUrl}")
	private String adminBaseURL;

	@RequestMapping(path = "/v1/signin", method = RequestMethod.POST)
	public ResponseEntity<String> signIn(@RequestBody SubscriberEntity subscriber) {
		String result = loginService.getLoginDetails(subscriber.getMobileNo(), subscriber.getPassWord());
		if (result.equals("Successful Login!")) {
			return ResponseEntity.ok().body("Successful Login!");
		} else if (result.equals("User Not Found!")) {
			return ResponseEntity.ok().body("User Not Found!");
		} else {
			return ResponseEntity.ok().body("Invalid Password! Please try again");
		}
	}

	@RequestMapping(path = "/v1/subscriber/{mobileNo}", method = RequestMethod.GET)
	public ResponseEntity<SubscriberEntity> getSubscriber(@PathVariable("mobileNo") long mobileNo) {
		SubscriberEntity subscriberEntity = subscriberService.getSubscriber(mobileNo);		
		 LOG.info("\n subscriber:                                    ************\n"+subscriberEntity);
		return subscriberEntity==null ? ResponseEntity.notFound().build() : ResponseEntity.ok(subscriberService.getSubscriber(mobileNo));
	}

	@RequestMapping(path = "/v1/offers", method = RequestMethod.GET)
	public ResponseEntity<List<OfferEntity>> getAllOffer() {
		 ResponseEntity<List<OfferEntity>> offerEntity = restTemplate.exchange(
				  adminBaseURL+"/v1/offers",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<OfferEntity>>(){});
		
				 for(OfferEntity offer : offerEntity.getBody()) {
					 offerRepository.save(offer);
				 }
		 return offerEntity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(offerEntity.getBody());
	}

	@RequestMapping(path = "/v1/register", method = RequestMethod.POST)
	public ResponseEntity<SubscriberEntity> registerSubscriber(@RequestBody Subscriber subscriber) {
		long mobileNo = subscriber.getMobileNo();
		LOG.info("Subscriber Mobile Number: " + mobileNo);

//		String url = "localhost:8888/api/v1/subscriber";
		ResponseEntity<SubscriberEntity> responseSubscriberEntity = null;
		SubscriberEntity subscriberEntity = null;
		try {
			/*adminBaseUrl = adminBaseUrl == null ? "" : adminBaseUrl + "v1/subscriber/" + mobileNo;
			LOG.info("adminBaseUrl:     ************       " + adminBaseUrl);
			responseSubscriberEntity = restTemplate.getForEntity(adminBaseUrl, SubscriberEntity.class);*/
			/*
			 * responseSubscriberEntity =
			 * restTemplate.getForEntity("http://localhost:8888/api/v1/subscriber/" +
			 * mobileNo, SubscriberEntity.class);
			 */
			
			 responseSubscriberEntity = restTemplate.getForEntity(adminBaseURL + "/v1/subscriber/" + mobileNo,
	                    SubscriberEntity.class);

			/*
			 * responseSubscriberEntity =
			 * restTemplate.getForEntity(myConfig.getAdminUrl()+"subscriber/" + mobileNo,
			 * SubscriberEntity.class);
			 */
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (responseSubscriberEntity != null) {
			responseSubscriberEntity.getBody().setPassWord(subscriber.getPassword());
			subscriberEntity = subscriberRepository.save(responseSubscriberEntity.getBody());
		}
		return subscriberEntity == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(subscriberEntity);

	}

	@RequestMapping(path = "/v1/payBill", method = RequestMethod.POST)
	public ResponseEntity<PayBillEntity> payBill(@RequestBody PayBillOfferDTO offer) {
		Date date = new Date();
		String endDate = (date.getDate()+15)+"-"+(date.getMonth()+1)+"-"+(date.getYear()+1900)+"";
		String startDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
		SubscriberEntity subscriberEntity = subscriberService.getSubscriber(offer.getMobileNo());
			return ResponseEntity.ok().body(
				payBillService.payBill(subscriberEntity.getSubscriberId(),subscriberEntity.getMobileNo(), offer.getOfferId(), offer.getAmountPaid(), startDate, endDate));
	}
	
	@RequestMapping(path = "/v1/offer/{offerId}", method = RequestMethod.GET)
	public ResponseEntity<OfferEntity> findSingleOffer(@PathVariable("offerId") int offerId) {
		return ResponseEntity.ok().body(subscriberService.findSingleOffer(offerId));
	}

	@RequestMapping(path = "/v1/offer", method = RequestMethod.POST)
	public ResponseEntity<OfferEntity> createSingleOffer(@RequestBody OfferDTO offer) {
		OfferEntity offerEntity = subscriberService.createSingleOffer(offer);
		return offerEntity == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(offerEntity);
	}

	@RequestMapping(path = "/v1/offer", method = RequestMethod.PUT)
	public ResponseEntity<OfferEntity> modifySingleOffer(@RequestBody OfferDTO offer) {
		OfferEntity offerEntity = subscriberService.modifySingleOffer(offer);
		return offerEntity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(offerEntity);
	}

	@RequestMapping(path = "/v1/offer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteSingleOffer(@PathVariable("id") int id) {
		if (subscriberService.deleteSingleOffer(id)) {
			return ResponseEntity.ok("Offer is deleted successfully");
		} else
			return ResponseEntity.notFound().build();
	}

	@RequestMapping(path = "/v1/service", method = RequestMethod.POST)
	public ResponseEntity<ServiceEntity> createService(@RequestBody ServiceDTO serviceDTO) {
		ServiceEntity serviceEntity = subscriberService.addServiceData(serviceDTO);
		return serviceEntity == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(serviceEntity);
	}

	@RequestMapping(path = "/v1/service", method = RequestMethod.PUT)
	public ResponseEntity<ServiceEntity> modifyService(@RequestBody ServiceDTO serviceDTO) {
		ServiceEntity serviceEntity = subscriberService.modifyService(serviceDTO);
		return serviceEntity == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(serviceEntity);
	}

	@RequestMapping(path = "/v1/service", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteService(@RequestBody ServiceDTO serviceDTO) {
		if (subscriberService.deleteService(serviceDTO)) {
			return ResponseEntity.ok("Service is deleted successfully");
		} else
			return ResponseEntity.notFound().build();
	}

	@RequestMapping(path = "/v1/service-group-id", method = RequestMethod.POST)
	public String createServiceGroupId(@RequestBody ArrayList<ServiceGroupDTO> serviceGroupList) {
		subscriberService.addServiceGroupData(serviceGroupList);
		return "Service group created";
	}

	@RequestMapping(path = "/v1/service-group", method = RequestMethod.POST)
	public ResponseEntity<ServiceGroupEntity> createServiceGroup(@RequestBody ServiceGroupDTO serviceGroupDTO) {
		ServiceGroupEntity serviceGroupEntity = subscriberService.addServiceGroup(serviceGroupDTO);
		return serviceGroupEntity == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(serviceGroupEntity);
	}

	@RequestMapping(path = "/v1/service-group", method = RequestMethod.PUT)
	public ResponseEntity<ServiceGroupEntity> modifyServiceGroup(@RequestBody ServiceGroupDTO serviceGroupDTO) {
		ServiceGroupEntity serviceGroupEntity = subscriberService.addServiceGroup(serviceGroupDTO);
		return serviceGroupEntity == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(serviceGroupEntity);
	}
	
	@RequestMapping(path = "/v1/subscriber", method = RequestMethod.PUT)
	public ResponseEntity<SubscriberEntity> modifySubscriberData(@RequestBody SubscriberDTO subscriber) {
		SubscriberEntity subscriberEntity = subscriberService.modifySubscriberData(subscriber);
		return subscriberEntity==null ? ResponseEntity.notFound().build() : 
			new ResponseEntity<SubscriberEntity>(subscriberEntity, HttpStatus.OK);
	} 

	public static String getAdminBaseUrl(String apiUrl) {
		return "http://localhost:8888/api/" + apiUrl;
	}
	
	@RequestMapping(path = "/v1/overview/{mobileNo}", method = RequestMethod.GET)
	public ResponseEntity<OverviewDTO> getoverview(@PathVariable("mobileNo") long mobileNo) {
		PayBillEntity payBillEntity = payBillRepository.findByMobileNo(mobileNo);
		if(payBillEntity == null) {
			return ResponseEntity.notFound().build();
		}else {
			OverviewDTO overviewDTO = new OverviewDTO(mobileNo, payBillEntity.getOfferId(), payBillEntity.getAmountPaid(), payBillEntity.getStartDate(), payBillEntity.getEndDate(), subscriberService.findSingleOffer(payBillEntity.getOfferId()).getOfferName());
			return overviewDTO==null ? ResponseEntity.notFound().build() : ResponseEntity.ok(overviewDTO);
		}
	}
}
