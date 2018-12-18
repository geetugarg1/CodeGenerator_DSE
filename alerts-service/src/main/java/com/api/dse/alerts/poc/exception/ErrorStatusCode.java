package com.api.dse.alerts.poc.exception;

public enum ErrorStatusCode {
	
	BAD_REQUEST(400),UNAUTHORIZED(401),FORBIDDEN(403),NOT_FOUND(404),METHOD_NOT_FOUND(405),NOT_ACCEPTABLE(406),PRECONDITION_REQUIRED(428),TOO_MANY_REQUESTS(429),INTERNAL_SERVER_ERROR(500),SERVICE_UNAVAILABLE(503);	
	
	private int code;
	
	ErrorStatusCode(int code){
		this.code=code;		
	}

	public int getCode() {
		return code;
	}
	
	public static ErrorStatusCode getStatusCode(int code) {
		switch(code){
			case 400: return BAD_REQUEST; 					
			case 401: return UNAUTHORIZED;
			case 403: return FORBIDDEN;
			case 404: return NOT_FOUND;
			case 405: return METHOD_NOT_FOUND;
			case 406: return NOT_ACCEPTABLE;
			case 428: return PRECONDITION_REQUIRED;
			case 429: return TOO_MANY_REQUESTS;			
			case 500: return INTERNAL_SERVER_ERROR;
			case 503: return SERVICE_UNAVAILABLE;
			default : return null;
		}
	}
	
}
