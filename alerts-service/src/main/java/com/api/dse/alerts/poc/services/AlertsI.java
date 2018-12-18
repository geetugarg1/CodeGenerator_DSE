package com.api.dse.alerts.poc.services;

import java.util.*;

import com.api.dse.alerts.poc.model.*;

public interface AlertsI {

	public AlertsResponseWrapper execute(AlertsP pojo);
	public <T> T error(int statusCode,Class<T> type,Exception exception) throws InstantiationException, IllegalAccessException;

}