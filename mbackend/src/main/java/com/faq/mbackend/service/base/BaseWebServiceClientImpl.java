package com.faq.mbackend.service.base;

import org.springframework.web.client.RestTemplate;

public class BaseWebServiceClientImpl {

	protected RestTemplate restTemplate;

	public BaseWebServiceClientImpl() {
	}

	public BaseWebServiceClientImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		MbackendResponseErrorHandler errorHandler = new MbackendResponseErrorHandler();
		restTemplate.setErrorHandler(errorHandler);
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		MbackendResponseErrorHandler errorHandler = new MbackendResponseErrorHandler();
		restTemplate.setErrorHandler(errorHandler);
	}

}
