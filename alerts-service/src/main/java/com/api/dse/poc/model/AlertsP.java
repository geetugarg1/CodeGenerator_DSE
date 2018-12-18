package com.api.dse.poc.model;

import java.util.*;

import org.joda.time.DateTime;

import org.joda.time.*;

import java.math.*;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AlertsP {

	private Long customerid;

	public AlertsP(){}

	public AlertsP(Long customerid){
		 this.customerid=customerid;
	}


	public Long getCustomerid() {
		 return customerid;
	}

	public void setCustomerid(Long customerid) {
		 this.customerid=customerid;
	}
}