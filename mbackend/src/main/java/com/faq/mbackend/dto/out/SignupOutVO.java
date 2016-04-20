package com.faq.mbackend.dto.out;

public class SignupOutVO extends BaseOutVO {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {
		private String signUpDate;
		private UserClientBoxOutVO user;

		public String getSignUpDate() {
			return signUpDate;
		}

		public void setSignUpDate(String signUpDate) {
			this.signUpDate = signUpDate;
		}

		public UserClientBoxOutVO getUser() {
			return user;
		}

		public void setUser(UserClientBoxOutVO user) {
			this.user = user;
		}
	}

}
