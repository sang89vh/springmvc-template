package com.faq.mbackend.service.impl;

import com.faq.mbackend.common.AppConfig;
import com.faq.mbackend.service.base.MbackendRequestCallback;
import com.faq.mbackend.service.base.MbackendResponse;
import com.faq.mbackend.service.base.MbackendResponseExtractor;
import com.faq.mbackend.dto.in.DubMakeVideoInVO;
import com.faq.mbackend.dto.in.DubUpdateVideoEffectInVO;
import com.faq.mbackend.dto.in.MakeEffectInVO;
import com.faq.mbackend.dto.out.DubUploadVideoOutVO;
import com.faq.mbackend.dto.out.ParseBaseOutVO;
import com.faq.mbackend.service.DubVideoService;
import com.faq.mbackend.exception.BaseException;
import com.faq.mbackend.exception.ErrorCodeCustomerEnum;
import com.faq.mbackend.exception.MbackendException;
import com.faq.mbackend.exception.RestError;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jack on 3/20/16.
 */
@Service
public class DubVideoServiceImpl extends BaseServiceImpl implements
        DubVideoService {
    @Override
    public ParseBaseOutVO saveVideo(DubMakeVideoInVO dubMakeVideoInVO) {

        logger.info("begin save video");
        MbackendResponse MbackendResponse = null;

        Map<String, String> headerAttrs = new HashMap<String, String>();
        headerAttrs.put(AppConfig.X_PARSE_APPLICATION_ID, AppConfig.PARSE_APP_ID);
        headerAttrs.put(AppConfig.X_PARSE_REST_API_KEY, AppConfig.PARSE_API_KEY);
        //headerAttrs.put("Content-Type", MediaType.APPLICATION_JSON.toString());

        RequestCallback requestCallback = new MbackendRequestCallback(headerAttrs,
                dubMakeVideoInVO, restTemplate.getMessageConverters());

        MbackendResponseExtractor responseExtractor = new MbackendResponseExtractor(
                ParseBaseOutVO.class,
                restTemplate.getMessageConverters());

        MbackendResponse = restTemplate.execute(
                AppConfig.PARSE_URL + "/parse/classes/" + AppConfig.DUB_VIDEO,
                HttpMethod.POST,
                requestCallback, responseExtractor);

        if (MbackendResponse == null) {
            // FIXME: need to log this
            logger.error("MbackendResponse is null");
            throw new MbackendException(ErrorCodeCustomerEnum.UNKNOWN_ERROR, "REST response is null");
        }

        if (!MbackendResponse.isFoundError()) {

            ParseBaseOutVO results = (ParseBaseOutVO) MbackendResponse
                    .getResponse();

            logger.info(results.getCreatedAt());
            logger.info(results.getObjectId());
            logger.info("End save video");
            return results;

        } else {
            // need to convert RestError back into exception and throw it
            RestError restError = MbackendResponse.getErrResponse();
            BaseException ex = restError.transformRestError();
            // can do i18n here, for now just go with default
            ex.setMessage(ex.getDebugMessage());
            throw ex;
        }





    }

    @Override
    public DubUploadVideoOutVO updateVideo(MakeEffectInVO makeEffectInVO) {
        logger.info("begin update video");
        MbackendResponse MbackendResponse = null;
        DubUpdateVideoEffectInVO inVO = new DubUpdateVideoEffectInVO();
        inVO.setUrlEffect(makeEffectInVO.getUrl());

        Map<String, String> headerAttrs = new HashMap<String, String>();
        headerAttrs.put(AppConfig.X_PARSE_APPLICATION_ID, AppConfig.PARSE_APP_ID);
        headerAttrs.put(AppConfig.X_PARSE_REST_API_KEY, AppConfig.PARSE_API_KEY);
        //headerAttrs.put("Content-Type", MediaType.APPLICATION_JSON.toString());

        RequestCallback requestCallback = new MbackendRequestCallback(headerAttrs,
                inVO, restTemplate.getMessageConverters());

        MbackendResponseExtractor responseExtractor = new MbackendResponseExtractor(
                ParseBaseOutVO.class,
                restTemplate.getMessageConverters());

        MbackendResponse = restTemplate.execute(
                AppConfig.PARSE_URL + "/parse/classes/" + AppConfig.DUB_VIDEO + "/" + makeEffectInVO.getVideoId(),
                HttpMethod.PUT,
                requestCallback, responseExtractor);

        if (MbackendResponse == null) {
            // FIXME: need to log this
            logger.error("MbackendResponse is null");
            throw new MbackendException(ErrorCodeCustomerEnum.UNKNOWN_ERROR, "REST response is null");
        }

        if (!MbackendResponse.isFoundError()) {

            ParseBaseOutVO results = (ParseBaseOutVO) MbackendResponse
                    .getResponse();
            logger.info(results.getUpdatedAt());


            DubUploadVideoOutVO dubUploadVideoOutVO = new DubUploadVideoOutVO();
            dubUploadVideoOutVO.setUrl(makeEffectInVO.getUrl());
            dubUploadVideoOutVO.setVideoId(makeEffectInVO.getVideoId());
            dubUploadVideoOutVO.setUrlEffect(makeEffectInVO.getUrl());

            logger.info("End update video");
            return dubUploadVideoOutVO;

        } else {
            // need to convert RestError back into exception and throw it
            RestError restError = MbackendResponse.getErrResponse();
            BaseException ex = restError.transformRestError();
            // can do i18n here, for now just go with default
            ex.setMessage(ex.getDebugMessage());
            throw ex;
        }
    }


}
