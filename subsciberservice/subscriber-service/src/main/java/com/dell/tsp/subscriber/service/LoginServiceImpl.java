package com.dell.tsp.subscriber.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dell.tsp.subscriber.dao.DataDaoImpl;

@Service
public class LoginServiceImpl implements LoginService{
	
	private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	DataDaoImpl dataDao;
	
	
	public String getLoginDetails(long mobileNo, String password) {
		log.info("Inside Service: getLoginDetails()");
		String passWord = dataDao.findByMobileNo(mobileNo);
		String status = "";
		
		if(passWord.equals(password)) {
			status = "Successful Login!";
		}
		
		if(passWord.equals("User not found")) {
			status = "User Not Found!";
		}
		
		if(!passWord.equals("User not found") && !passWord.equals(password)){
			status = "Invalid Password! Please try again";
		}
		return status;
	}

}
