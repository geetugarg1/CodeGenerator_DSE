package com.api.dse.poc.services.impl;

import java.util.*;

import com.api.dse.poc.model.*;

import com.api.dse.poc.dao.*;
import com.api.dse.poc.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
@Service
public class AlertsImpl implements AlertsI {

	@Override
	@HystrixCommand(fallbackMethod = "reliable")
	public AlertsResponseWrapper execute(AlertsP pojo){
		//TODO
		return new AlertsResponseWrapper();
	}

	public AlertsResponseWrapper reliable(AlertsP pojo){
		//TODO
		return new AlertsResponseWrapper();
	}
	@Override
	public <T> T error(int statusCode, Class<T> type,Exception exception)
			throws InstantiationException, IllegalAccessException {
		//TODO to write error response
		return type.newInstance();
	}}