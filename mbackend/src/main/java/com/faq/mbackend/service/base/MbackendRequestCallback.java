package com.faq.mbackend.service.base;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.RequestCallback;

public class MbackendRequestCallback implements RequestCallback {
	
	private List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	private Map<String, String> headerAttrs;
	private Object requestBody;
	
	public MbackendRequestCallback() { }
	
	public MbackendRequestCallback(Map<String, String> headerAttrs) {
		this.headerAttrs = headerAttrs;
		this.requestBody = null;
	}
	
	public MbackendRequestCallback(Map<String, String> headerAttrs, Object requestBody, 
			List<HttpMessageConverter<?>> messageConverters) {
		this.headerAttrs = headerAttrs;
		this.requestBody = requestBody;
		this.messageConverters = messageConverters;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void doWithRequest(ClientHttpRequest request) throws IOException {
		// first set up the header
		request.getHeaders().set( "Accept", "application/json" );
		request.getHeaders().set("Content-Type", MediaType.APPLICATION_JSON.toString());
		if (headerAttrs != null) {
			for (String key : headerAttrs.keySet()) {
				String value = headerAttrs.get(key);
				request.getHeaders().set(key, value);
			}
		}
		
		// next if there is a body add it to request
		if (requestBody != null){
			Assert.notEmpty(messageConverters, "'messageConverters' must not be empty");
			Class<?> requestType = requestBody.getClass();
			for (HttpMessageConverter messageConverter : messageConverters) {
				if (messageConverter.canWrite(requestType, MediaType.APPLICATION_JSON)) {
					messageConverter.write(requestBody, MediaType.APPLICATION_JSON, request);
					break;
				}
			}
		}
	}
	
	public void setHeaderAttrs(Map<String, String> headerAttrs) {
		this.headerAttrs = headerAttrs;
	}
	
	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}
	
	public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
		this.messageConverters = messageConverters;
	}

}
