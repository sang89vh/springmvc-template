package com.faq.mbackend.exception;

public enum ServiceEnum {
	
	CUSTOMER_SERVICE(1, "ErrorCodeCustomerEnum"),
	;
	
	private int serviceId;
	private String serviceEnumName;
	
	
	ServiceEnum(int serviceId, String serviceEnumName) {
		this.serviceId = serviceId;
		this.serviceEnumName = serviceEnumName;
	}
	
	public int getServiceId() {
		return serviceId;
	}
	
	public String getServiceEnumName(){
		return serviceEnumName;
	}
	
	static public BaseException createServiceException(RestError restError) {
		switch (restError.getServiceId()){
		case 1:
			ErrorCodeEnum errorCodeEnum = ErrorCodeCustomerEnum.get(restError.getBb_code());
			return new MbackendException(errorCodeEnum, 
					restError.getMessage(), restError.getResource());

		// FIXME: add other services here

		default:
			return null;
		}
		
	}

}
