package com.faq.mbackend.dto.in;

/**
 * Created by jack on 3/31/16.
 */
public class MakeEffectInVO extends BaseParseInVO{

    private String userId;
    private String videoId;
    private String url;
    private String effectCode;

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

    public String getEffectCode() {
        return effectCode;
    }

    public void setEffectCode(String effectCode) {
        this.effectCode = effectCode;
    }
}
