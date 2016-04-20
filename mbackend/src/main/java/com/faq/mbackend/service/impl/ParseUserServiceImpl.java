package com.faq.mbackend.service.impl;

import com.faq.mbackend.common.AppConfig;
import com.faq.mbackend.dto.in.DubUpdateVideoEffectInVO;
import com.faq.mbackend.dto.out.DubUploadVideoOutVO;
import com.faq.mbackend.dto.out.ParseBaseOutVO;
import com.faq.mbackend.dto.out.ParseSessionOutVO;
import com.faq.mbackend.exception.BaseException;
import com.faq.mbackend.exception.ErrorCodeCustomerEnum;
import com.faq.mbackend.exception.MbackendException;
import com.faq.mbackend.exception.RestError;
import com.faq.mbackend.service.ParseUserService;
import com.faq.mbackend.service.base.MbackendRequestCallback;
import com.faq.mbackend.service.base.MbackendResponse;
import com.faq.mbackend.service.base.MbackendResponseExtractor;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jack on 4/2/16.
 */
@Service
public class ParseUserServiceImpl extends BaseServiceImpl implements ParseUserService {

	@Override
	public ParseSessionOutVO getParseSession(String parseToken) throws MbackendException {
		logger.info("begin getParseSession");
		logger.info("token:{}", parseToken);

		if (null == parseToken || "".equals(parseToken.trim())) {
			logger.info("token is null or tempty");
			throw new MbackendException(ErrorCodeCustomerEnum.PERMISSION_ERROR, "Permission error");
		}

		MbackendResponse mbackendResponse = null;

		Map<String, String> headerAttrs = new HashMap<String, String>();
		headerAttrs.put(AppConfig.X_PARSE_APPLICATION_ID, AppConfig.PARSE_APP_ID);
		headerAttrs.put(AppConfig.X_PARSE_REST_API_KEY, AppConfig.PARSE_API_KEY);
		headerAttrs.put(AppConfig.X_PARSE_SESSION_TOKEN, parseToken);
		MultiValueMap<String, String> parameter = new LinkedMultiValueMap<String, String>();

		RequestCallback requestCallback = new MbackendRequestCallback(headerAttrs, parameter,
				restTemplate.getMessageConverters());

		MbackendResponseExtractor responseExtractor = new MbackendResponseExtractor(String.class,
				restTemplate.getMessageConverters());
		try {

			mbackendResponse = restTemplate.execute(AppConfig.PARSE_URL + "/parse/sessions/me", HttpMethod.GET,
					requestCallback, responseExtractor);

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error getParseSession:{}", e.getMessage());
		}

		if (mbackendResponse == null) {
			// FIXME: need to log this
			logger.error("MbackendResponse is null");
			throw new MbackendException(ErrorCodeCustomerEnum.PERMISSION_ERROR, "Permission error");
		}

		if (!mbackendResponse.isFoundError()) {

			ParseSessionOutVO results = new ParseSessionOutVO();

			String resultsString = (String) mbackendResponse.getResponse();

			try {
				JSONObject jsonObject = new JSONObject(resultsString);

				results.setObjectId(jsonObject.getString("objectId"));
				
				results.setSessionToken(jsonObject.getString("sessionToken"));
				
				results.setUser(jsonObject.getJSONObject("user").toString());
				
				results.setCreatedWith(jsonObject.getJSONObject("createdWith").toString());

			} catch (JSONException jse) {
				
				logger.error("parse jsonobject error:{}", jse.getMessage(), jse);

			}

			logger.info("respond:{}", resultsString);

			logger.info("session token:{}", results.getSessionToken());

			logger.info("End getParseSession");

			return results;

		} else {

			logger.info("Resttemplate request error");
			// need to convert RestError back into exception and throw it
			RestError restError = mbackendResponse.getErrResponse();

			logger.info("resttemplate error:{}", "" + restError.getMessage());

			BaseException ex = restError.transformRestError();
			// can do i18n here, for now just go with default
			ex.setMessage(ex.getDebugMessage());
			throw ex;
		}
	}
}
