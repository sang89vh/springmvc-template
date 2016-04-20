package com.faq.mbackend.dto.in;

public class BoxSignupInVO {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public static BoxSignupInVO parseToBoxSignupInVO(SignupInVO signupInVO){
		BoxSignupInVO BoxSignupInVO=new BoxSignupInVO();
		BoxSignupInVO.setUsername(signupInVO.getEmail());
		BoxSignupInVO.setPassword(signupInVO.getPassword());
		return BoxSignupInVO;
	}

}
