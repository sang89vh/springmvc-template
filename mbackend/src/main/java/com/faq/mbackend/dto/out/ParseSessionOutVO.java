package com.faq.mbackend.dto.out;

/**
 * Created by jack on 4/2/16.
 */
public class ParseSessionOutVO extends BaseOutVO {

    private String objectId;
    private String sessionToken;
    private String user;
    private String restricted;
    private String expiresAt;
    private String updatedAt;
    private String createdAt;
    
    private String createdWith;
    
    

    public String getCreatedWith() {
		return createdWith;
	}

	public void setCreatedWith(String createdWith) {
		this.createdWith = createdWith;
	}

	public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRestricted() {
        return restricted;
    }

    public void setRestricted(String restricted) {
        this.restricted = restricted;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

   


}


