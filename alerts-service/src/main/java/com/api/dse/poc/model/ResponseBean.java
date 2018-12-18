package com.api.dse.poc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class ResponseBean<T> 
{
	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String STATUS_ERROR = "ERROR";

	private int statusCode;
	private String status;
	private String errorMessage;
	private Object errDetails;
	private T response;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errMsg) {
		this.errorMessage = errMsg;
	}

	public Object getErrDetails() {
		return errDetails;
	}

	public void setErrDetails(Object errDetails) {
		this.errDetails = errDetails;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "ResponseBean [statusCode=" + statusCode + ", status=" + status + ", errorMessage=" + errorMessage
				+ ", errDetails=" + errDetails + ", response=" + response + "]";
	}

}
