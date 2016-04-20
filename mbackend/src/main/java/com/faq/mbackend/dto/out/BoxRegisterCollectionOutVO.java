package com.faq.mbackend.dto.out;

public class BoxRegisterCollectionOutVO extends BaseOutVO {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data extends BaseBoxCollectionData {
		private String collectionName;
		private String userName;
		public String getCollectionName() {
			return collectionName;
		}
		public void setCollectionName(String collectionName) {
			this.collectionName = collectionName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		

	}
}
