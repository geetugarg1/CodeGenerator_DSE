package com.api.dse.poc.services;

import java.util.*;

import com.api.dse.poc.model.*;

public interface AlertsI {

	public AlertsResponseWrapper execute(AlertsP pojo);
	public <T> T error(int statusCode,Class<T> type,Exception exception) throws InstantiationException, IllegalAccessException;

}