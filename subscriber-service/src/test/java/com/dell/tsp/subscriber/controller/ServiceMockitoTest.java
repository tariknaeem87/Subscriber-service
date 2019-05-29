package com.dell.tsp.subscriber.controller;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.dell.tsp.subscriber.dao.DataDaoImpl;
import com.dell.tsp.subscriber.dto.LoginDTO;
import com.dell.tsp.subscriber.dto.OfferDTO;
import com.dell.tsp.subscriber.dto.SubscriberDTO;
import com.dell.tsp.subscriber.service.LoginService;
import com.dell.tsp.subscriber.service.PayBillService;
import com.dell.tsp.subscriber.service.SubscriberService;

@RunWith(MockitoJUnitRunner.class)
public class ServiceMockitoTest {
	
	@InjectMocks
	private SubscriberBaseController subscriberControllermock = new SubscriberBaseController();
	
	@Mock
	private SubscriberService subscriberService;
	
	@Mock
	private LoginService loginService;
	
	@Mock
	private PayBillService payBillService;
	
	@Mock
	private SubscriberDTO DTOmock;
	
	
	@InjectMocks 
	private LoginDTO mockLoginDTO;
	 
	
	@Mock
	private OfferDTO offerDTO;
	
	@Autowired
	DataDaoImpl dataDao;
	
	
	
	@Before
	  public void setup() { 
		  MockitoAnnotations.initMocks(this);
	  } 
	
	@Test
	public void TestGetUserdata() {
	
	}
	/*
	 * when(subscriberService.saveSubscriber(DTOmock)).thenReturn("1"); String
	 * responseReturned= homeControllermock.getUserdata(DTOmock);
	 * assertThat(responseReturned, equalTo("1")); }
	 */
	
	/*
	 * @Test public void TestSignIndata() {
	 * when(loginService.getLoginDetails(mockLoginDTO.getUserName(),
	 * mockLoginDTO.getPassWord())).thenReturn("1"); String responseReturned=
	 * homeControllermock.signIn(mockLoginDTO); assertThat(responseReturned,
	 * equalTo("1")); }
	 */
	
	@Test
	public void TestPayBilldata() {
		/*
		 * when(payBillService.payBill(offerDTO.getSubscriberId(),
		 * offerDTO.getOfferId(), offerDTO.getAmountPaid())).thenReturn("1"); String
		 * responseReturned= homeControllermock.payBill(offerDTO);
		 * assertThat(responseReturned, equalTo("1"));
		 */
	}
	
	
}
