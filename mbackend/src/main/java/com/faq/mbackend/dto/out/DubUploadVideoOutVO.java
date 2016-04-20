package com.faq.mbackend.dto.out;

public class DubUploadVideoOutVO  extends BaseOutVO{
	private String url;
	private String urlEffect;
	private String userId;
	private String videoId;
	private String title;
	private String tag;
	private int status = 0;
	private int privacy = 0;
	private int isDelete = 0;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlEffect() {
		return urlEffect;
	}

	public void setUrlEffect(String urlEffect) {
		this.urlEffect = urlEffect;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
