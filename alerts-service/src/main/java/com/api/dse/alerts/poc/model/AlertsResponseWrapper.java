package com.api.dse.alerts.poc.model;

import java.util.*;

import org.joda.time.DateTime;

import org.joda.time.*;

import java.math.*;

import org.springframework.util.MultiValueMap;

import org.springframework.util.LinkedMultiValueMap;

public class AlertsResponseWrapper {

	 private AlertObject response;

	 public MultiValueMap<String, String> getHeaders(){
	 final MultiValueMap<String, String> map=new LinkedMultiValueMap<String, String>();
 	return map;
	}
	 public void setHeaders(){
		 
	} 
	public AlertObject getResponse(){
		 return response;
	} 
	 public void setResponse(AlertObject response){
		 this.response=response;
	} 
}