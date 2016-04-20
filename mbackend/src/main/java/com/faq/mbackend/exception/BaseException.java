package com.faq.mbackend.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -8961905267911341174L;

	protected String message;
	protected ErrorCodeEnum errorCode;
	protected String debugMessage;
	protected String messageArgs;
	private String resource;

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public String getMessageArgs() {
		return messageArgs;
	}

	public void setMessageArgs(String messageArgs) {
		this.messageArgs = messageArgs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RestError transformException(int httpStatus) {
		RestError restError = new RestError();
		restError.setHttp_code(httpStatus);
		restError.setBb_code(errorCode.getBb_code());
		restError.setResult(errorCode.getResult());
		restError.setResource(errorCode.getResource());		
		restError.setMethod(errorCode.getMethod());
		restError.setAPI_version(errorCode.getAPI_version());
		restError.setData(resource);
		restError.setMessage(debugMessage);
		return restError;
	}

}
