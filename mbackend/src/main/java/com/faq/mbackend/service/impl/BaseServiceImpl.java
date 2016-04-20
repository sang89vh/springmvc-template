package com.faq.mbackend.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.faq.mbackend.service.base.BaseWebServiceClientImpl;
import com.faq.mbackend.exception.MbackendException;
import com.faq.mbackend.exception.ErrorCodeCustomerEnum;

public class BaseServiceImpl extends BaseWebServiceClientImpl {
	/** Logger available to subclasses */

	public final Logger logger = LoggerFactory.getLogger(getClass());
	protected HttpHeaders requestHeaders;

	public BaseServiceImpl() {
		super(new RestTemplate());
		requestHeaders = new HttpHeaders();
	}

	public Object postForm(String url, MultiValueMap<String, String> parameter,
			Class<?> objectType) throws MbackendException {

		requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				parameter, requestHeaders);

		ResponseEntity<?> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.POST,
					requestEntity, objectType);
		} catch (RestClientException rex) {
			throw new MbackendException(ErrorCodeCustomerEnum.PERMISSION_ERROR);
		}
		Object BoxOutVO = null;
		if (response.getStatusCode() == HttpStatus.OK) {
			BoxOutVO = response.getBody();
		} else {
			throw new MbackendException(ErrorCodeCustomerEnum.PERMISSION_ERROR);
		}
		return BoxOutVO;
	}

	public Object postJson() {
		return null;
	}

	public Object doGet(String url, MultiValueMap<String, String> parameter,
			Class<?> objectType) throws MbackendException {
		requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
				parameter, requestHeaders);

		ResponseEntity<?> response = null;
		try {
			response = restTemplate.exchange(url, HttpMethod.GET,
					requestEntity, objectType);
		} catch (RestClientException rex) {
			throw new MbackendException(ErrorCodeCustomerEnum.PERMISSION_ERROR);
		}
		Object BoxOutVO = null;
		if (response.getStatusCode() == HttpStatus.OK) {
			BoxOutVO = response.getBody();
		} else {
			throw new MbackendException(ErrorCodeCustomerEnum.PERMISSION_ERROR);
		}
		return BoxOutVO;
	}

	public Object put() {
		return null;
	}
}
