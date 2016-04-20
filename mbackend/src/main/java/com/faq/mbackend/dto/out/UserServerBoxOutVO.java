package com.faq.mbackend.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserServerBoxOutVO extends UserClientBoxOutVO {

	@JsonProperty("X-BB-SESSION")
	private String X_BB_SESSION;

	public String getX_BB_SESSION() {
		return X_BB_SESSION;
	}

	public void setX_BB_SESSION(String x_BB_SESSION) {
		X_BB_SESSION = x_BB_SESSION;
	}

}
