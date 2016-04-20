package com.faq.mbackend.dto.out;

public class BoxCreateCollectionOutVO extends BaseOutVO {

	private String data;
	private String message;


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
