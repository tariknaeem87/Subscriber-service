package com.dell.tsp.subscriber.model;

public class Subscriber {
	
    private String firstName;
    private String password;
    private String adharNo;
    private String address;
    private String lastName;
    private String email;
    private long mobileNo;
    
	public Subscriber() {
		super();
	}
	public Subscriber(String firstName, String password, String adharNo, String address, String lastName, String email,
			long mobileNo) {
		super();
		this.firstName = firstName;
		this.password = password;
		this.adharNo = adharNo;
		this.address = address;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
    
    
}
