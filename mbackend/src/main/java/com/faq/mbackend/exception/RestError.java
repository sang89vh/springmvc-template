package com.faq.mbackend.exception;


public class RestError {
	
	private int serviceId=1;
	private int http_code;
	private String bb_code;
	private String result ="ERROR";
	private String resource;
	private String method;
	private String data;
	private String message ;
	
	private String API_version;
	
	
	
	
	public String getAPI_version() {
		return API_version;
	}
	public void setAPI_version(String aPI_version) {
		API_version = aPI_version;
	}
	public int getHttp_code() {
		return http_code;
	}
	public void setHttp_code(int http_code) {
		this.http_code = http_code;
	}
	
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	
	public String getBb_code() {
		return bb_code;
	}
	public void setBb_code(String bb_code) {
		this.bb_code = bb_code;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
		
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public BaseException transformRestError() {
		return ServiceEnum.createServiceException(this);
	}
	/**
	 * 
	 * @return 1 when service is Box
	 */
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

}
