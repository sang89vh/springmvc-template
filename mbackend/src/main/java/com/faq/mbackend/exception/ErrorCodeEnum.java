package com.faq.mbackend.exception;

public interface ErrorCodeEnum {
	
	String  getResult();
	String getName();
	String  getBb_code();
	String getMessageKey();
	String getDefaultMessage();
	String getResource();
	String getMethod();
	String getAPI_version();

}
