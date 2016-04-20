package com.faq.mbackend.dto.out;

public class BaseOutVO{
	private String result = "OK";
	private Integer http_code = 200;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Integer getHttp_code() {
		return http_code;
	}

	public void setHttp_code(Integer http_code) {
		this.http_code = http_code;
	}

}
