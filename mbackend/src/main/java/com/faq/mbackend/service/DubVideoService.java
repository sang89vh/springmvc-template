package com.faq.mbackend.service;

import com.faq.mbackend.dto.in.DubMakeVideoInVO;
import com.faq.mbackend.dto.in.MakeEffectInVO;
import com.faq.mbackend.dto.out.DubUploadVideoOutVO;
import com.faq.mbackend.dto.out.ParseBaseOutVO;

/**
 * Created by jack on 3/20/16.
 */
public interface DubVideoService {

    /**
     * curl -X POST \
     * -H "X-Parse-Application-Id: ${APPLICATION_ID}" \
     * -H "X-Parse-REST-API-Key: ${REST_API_KEY}" \
     * -H "Content-Type: application/json" \
     * -d '{"score":1337,"playerName":"Sean Plott","cheatMode":false}' \
     * https://api.parse.com/classes/GameScore
     *
     * @param dubMakeVideoInVO
     */
    public abstract ParseBaseOutVO saveVideo(DubMakeVideoInVO dubMakeVideoInVO);

    /**
     * curl -X PUT \
     * -H "X-Parse-Application-Id: ${APPLICATION_ID}" \
     * -H "X-Parse-REST-API-Key: ${REST_API_KEY}" \
     * -H "Content-Type: application/json" \
     * -d '{"score":73453}' \
     * https://api.parse.com/classes/GameScore/Ed1nuqPvcm
     *
     * @param makeEffectInVO
     */
    public abstract DubUploadVideoOutVO updateVideo(MakeEffectInVO makeEffectInVO);


}
