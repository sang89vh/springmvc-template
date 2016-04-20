package com.faq.mbackend.dto.out;

import java.util.List;

public class UserClientBoxOutVO {
	private String name;
	private String status;
	private List<RoleBoxOutVO> roles;
	private String signUpDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RoleBoxOutVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBoxOutVO> roles) {
		this.roles = roles;
	}

	public String getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}
}
