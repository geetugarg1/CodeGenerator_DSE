package com.api.dse.poc.audit;

//import org.springframework.data.mongodb.core.mapping.Document;

//@Document
public final class AuditLog {

	private String              id;
	private String				resource;
	private String 				httpMethod;
	private String 				requestUri;
	private String 				remoteHost;
	private String 				ipAddr;
	private String 				elapsedTime;
	private String 				startTime;
	private String 				input;
	private String 				requestParams;
	private int 				httpStatusCode;
	
	
	public AuditLog() {
		super();
	}
	
	public String getResource() {
		return resource;
	}

	public void setResource(String messageKey) {
		this.resource = messageKey;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int status) {
		this.httpStatusCode = status;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	
	public String getRequestParams() {
		return requestParams;
	}

	public void setRequestParams(String rquestParams) {
		this.requestParams = rquestParams;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AuditLogBean [resource=" + resource + ", httpMethod=" + httpMethod + ", requestUri="
				+ requestUri + ", remoteHost=" + remoteHost + ", ipAddr=" + ipAddr + ", elapsedTime=" + elapsedTime
				+ ", startTime=" + startTime + ", httpStatusCode=" + httpStatusCode + "]";
	}
}
