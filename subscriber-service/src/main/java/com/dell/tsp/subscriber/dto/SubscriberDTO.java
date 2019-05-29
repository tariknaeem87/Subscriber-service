package com.dell.tsp.subscriber.dto;

public class SubscriberDTO {
	
    private long subscriberId;
    private String firstName;
    private String adharNo;
    private String address;
    private String lastName;
    private String email;
    private long mobileNo;
    private String passWord;
    
	public SubscriberDTO() {
		super();
	}
	
	public SubscriberDTO(long subscriberId, String firstName, String adharNo, String address, String lastName,
			String email, long mobileNo, String passWord) {
		super();
		this.subscriberId = subscriberId;
		this.firstName = firstName;
		this.adharNo = adharNo;
		this.address = address;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.passWord = passWord;
	}


	public long getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(long subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAdharNo() {
		return adharNo;
	}
	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}       
}
