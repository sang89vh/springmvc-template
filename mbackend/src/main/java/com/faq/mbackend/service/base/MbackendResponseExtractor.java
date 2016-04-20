package com.faq.mbackend.service.base;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;

import com.faq.mbackend.exception.RestError;

@SuppressWarnings("rawtypes")
public class MbackendResponseExtractor implements ResponseExtractor<MbackendResponse> {
	
	private final Class<?> responseType;
	private final List<HttpMessageConverter<?>> messageConverters;
	private HttpMessageConverter stringConverter;
	private boolean passThru = false;

	public MbackendResponseExtractor(Class<?> responseType,
			List<HttpMessageConverter<?>> messageConverters) {
		Assert.notNull(responseType, "'responseType' must not be null");
		Assert.notEmpty(messageConverters, "'messageConverters' must not be empty");
		this.responseType = responseType;
		this.messageConverters = messageConverters;
	}
	
	
	public MbackendResponseExtractor(Class<?> responseType,
			List<HttpMessageConverter<?>> messageConverters, boolean passThru) {
		Assert.notNull(responseType, "'responseType' must not be null");
		Assert.notEmpty(messageConverters, "'messageConverters' must not be empty");
		this.responseType = responseType;
		this.messageConverters = messageConverters;
		this.passThru = passThru;
		for (HttpMessageConverter converter : messageConverters){
			if (converter instanceof org.springframework.http.converter.StringHttpMessageConverter) {
				this.stringConverter = converter;
				break;
			}
		}
	}
	
	
	@Override
	public MbackendResponse extractData(ClientHttpResponse response) throws IOException {
		MbackendResponse pngResponse = new MbackendResponse();
//		if (response.getStatusCode() == HttpStatus.OK) {
		if (!hasError(response.getStatusCode())) {
			pngResponse.setResponse(extractResponseData(response));
			pngResponse.setFoundError(false);
		}
		else {
			pngResponse.setErrResponse(extractErrorData(response));
			pngResponse.setFoundError(true);
		}
		return pngResponse;
	}
	
	protected boolean hasError(HttpStatus statusCode) {
		if (statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
			return false;
		}
		return (
//				statusCode.series() == HttpStatus.Series.CLIENT_ERROR || 
				statusCode
				.series() == HttpStatus.Series.SERVER_ERROR);
	}
	@SuppressWarnings("unchecked")
	private Object extractResponseData(ClientHttpResponse response) throws IOException {
		MediaType contentType = response.getHeaders().getContentType();
		if (contentType == null) {
			throw new RestClientException("Cannot extract response: no Content-Type found");
		}
		if (passThru && stringConverter != null){
			return stringConverter.read(this.responseType, response);
		}
		else {
			for (HttpMessageConverter messageConverter : messageConverters) {
				if (messageConverter.canRead(responseType, contentType)) {
					return messageConverter.read(this.responseType, response);
				}
			}
		}
		throw new RestClientException(
				"Could not extract response: no suitable HttpMessageConverter found for response type [" +
						this.responseType.getName() + "] and content type [" + contentType + "]");
	}
	
	@SuppressWarnings("unchecked")
	private RestError extractErrorData(ClientHttpResponse response) throws IOException {
		MediaType contentType = response.getHeaders().getContentType();
		for (HttpMessageConverter messageConverter : messageConverters) {
			if (messageConverter.canRead(RestError.class, contentType)) {
				return (RestError) messageConverter.read(RestError.class, response);
			}
		}
		RestError restErr = new RestError();
		return restErr;
	}

}
