package com.faq.mbackend.dto.out;

public class DubUploadAudioOutVO {
	
	private String userId;

	private String title;
	private String tag;
	private int privacy = 0;
	private int isDelete = 0;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getPrivacy() {
		return privacy;
	}
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	
	


}
