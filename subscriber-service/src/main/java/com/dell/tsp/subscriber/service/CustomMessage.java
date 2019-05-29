package com.dell.tsp.subscriber.service;

import java.io.Serializable;
import java.sql.Date;

import com.dell.tsp.subscriber.model.*;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CustomMessage implements Serializable {
	
	public CustomMessage() {
		super();
	}


	private String to;
	private String from;
	private long subscriberId;
	private String cc;
	private String bcc;
	

	public void setTo(String to) {
		this.to = to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setSubscriberId(long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setDate(String date) {
		this.date = date;
	}


	private String subject;
	private String body;
	private String date;
	
	public CustomMessage(@JsonProperty("to") String to,
            			 @JsonProperty("from") String from,
            			 @JsonProperty("subscriberId") long subscriberId,
				         @JsonProperty("cc") String cc,
				         @JsonProperty("bcc") String bcc,
				         @JsonProperty("subject") String subject,
				         @JsonProperty("body") String body,
				         @JsonProperty("date") String date) {
    	
    	this.to =to;
    	this.from =from;
    	this.subscriberId =subscriberId;
    	this.cc =cc;
    	this.bcc =bcc;
    	this.subject =subject;
    	this.body =body;
    	this.date =date;
    	
    }
		
	public String getCc() {
        return cc;
    }
	
	public String getTo() {
        return to;
    }
	
	public String getFrom() {
        return from;
    }
	
	public long getSubscriberId() {
        return subscriberId;
    }
	
	public String getBcc() {
        return bcc;
    }
	
	public String getSubject() {
        return subject;
    }
	
	public String getBody() {
        return body;
    }
	
	public String getDate() {
        return date;
    }
	
	
    @Override
    public String toString() {
        return "CustomMessage{" +
                ", cc=" + cc +
                ", to=" + to +
                ",from="+ from +
                ",subscriberId=" + subscriberId +
                ",bcc=" + bcc +
                ",subject=" + subject +
                ",body=" + body +
                ",date=" + date + 
                '}';
    }

}
