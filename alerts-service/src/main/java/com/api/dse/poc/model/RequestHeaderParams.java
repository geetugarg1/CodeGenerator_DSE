package com.api.dse.poc.model;

import javax.persistence.*;

public class RequestHeaderParams {

	private String timestamp;
	private String channel;
	private String numberOfBytes;
	
	public RequestHeaderParams(){
		
	}
	public RequestHeaderParams(String timestamp,  String channel, String numberOfBytes) {
		super();
		this.timestamp = timestamp;
		this.channel = channel;
		this.setNumberOfBytes(numberOfBytes);
	}
	
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * @return the numberOfBytes
	 */
	public String getNumberOfBytes() {
		return numberOfBytes;
	}

	/**
	 * @param numberOfBytes the numberOfBytes to set
	 */
	public void setNumberOfBytes(String numberOfBytes) {
		this.numberOfBytes = numberOfBytes;
	}



	
}
