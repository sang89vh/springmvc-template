package com.faq.mbackend.service.base;

import com.faq.mbackend.exception.RestError;

public class MbackendResponse {
	
	private Object response;
	private RestError errResponse;
	private boolean foundError;
	
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response){
		this.response = response;
	}
	
	public RestError getErrResponse() {
		return errResponse;
	}
	public void setErrResponse(RestError errResponse) {
		this.errResponse = errResponse;
	}
	
	public boolean isFoundError() {
		return foundError;
	}
	public void setFoundError(boolean foundError) {
		this.foundError = foundError;
	}

}
