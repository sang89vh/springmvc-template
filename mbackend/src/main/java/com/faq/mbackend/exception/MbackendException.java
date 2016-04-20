package com.faq.mbackend.exception;

public class MbackendException extends BaseException {
	
	private static final long serialVersionUID = 8823356956725033191L;

	public MbackendException(ErrorCodeEnum errorCode) {
		super();
		this.setErrorCode(errorCode);
	}
	
	public MbackendException(ErrorCodeEnum errorCode, String debugMessage) {
		super();
		this.setErrorCode(errorCode);
		this.setDebugMessage(debugMessage);
	}
	
	public MbackendException(ErrorCodeEnum errorCode, String debugMessage, 
			String messageArgs) {
		super();
		this.setErrorCode(errorCode);
		this.setDebugMessage(debugMessage);
		this.messageArgs = messageArgs;
	}

}
