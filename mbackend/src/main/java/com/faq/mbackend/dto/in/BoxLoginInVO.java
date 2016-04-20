package com.faq.mbackend.dto.in;

import com.faq.mbackend.common.AppConfig;

public class BoxLoginInVO {

	private String username = AppConfig.BOX_USERNAME;
	private String password = AppConfig.BOX_PASSWORD;
	private String appcode = AppConfig.BOX_APPCODE;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAppcode() {
		return appcode;
	}

}
