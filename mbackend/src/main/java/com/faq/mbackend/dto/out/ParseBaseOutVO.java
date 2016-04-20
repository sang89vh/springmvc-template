package com.faq.mbackend.dto.out;

/**
 * Created by jack on 3/20/16.
 */
public class ParseBaseOutVO extends BaseOutVO{

    private String createdAt;
    private String objectId;
    private String updatedAt;



    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
