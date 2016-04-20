package com.faq.mbackend.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.faq.mbackend.common.AppConfig;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;

import com.faq.mbackend.common.BoxRoutes;
import com.faq.mbackend.service.base.MbackendRequestCallback;
import com.faq.mbackend.service.base.MbackendResponse;
import com.faq.mbackend.service.base.MbackendResponseExtractor;
import com.faq.mbackend.dto.in.BoxLoginInVO;
import com.faq.mbackend.dto.in.BoxSignupInVO;
import com.faq.mbackend.dto.in.SignupInVO;
import com.faq.mbackend.dto.out.BoxLoginOutVO;
import com.faq.mbackend.dto.out.SignupOutVO;
import com.faq.mbackend.service.BaasboxUserService;
import com.faq.mbackend.exception.BaseException;
import com.faq.mbackend.exception.MbackendException;
import com.faq.mbackend.exception.RestError;

@Service
public class BaasboxUserServiceImpl extends BaseServiceImpl implements BaasboxUserService {

	public BoxLoginOutVO login(BoxLoginInVO BoxLoginInVO) throws MbackendException {
		BoxLoginOutVO BoxLoginOutVO = null;

		logger.info("user service impl running!");

		MultiValueMap<String, String> parameter = new LinkedMultiValueMap<String, String>();
		parameter.add("username", BoxLoginInVO.getUsername());
		parameter.add("password", BoxLoginInVO.getPassword());
		parameter.add("appcode", BoxLoginInVO.getAppcode());

		BoxLoginOutVO = (BoxLoginOutVO) postForm(BoxRoutes.BOX_ACCOUNT_LOGIN, parameter, BoxLoginOutVO.class);

		return BoxLoginOutVO;
	}

	@Override
	public SignupOutVO signup(SignupInVO signupInVO, String xBBSession) throws MbackendException {
		MbackendResponse mbackendResponse = null;
		Map<String, String> headerAttrs = new HashMap<String, String>();
		headerAttrs.put(AppConfig.X_BB_SESSION, xBBSession);
		headerAttrs.put(AppConfig.X_BOX_APPCODE, AppConfig.BOX_APPCODE);

		RequestCallback requestCallback = new MbackendRequestCallback(headerAttrs,
				BoxSignupInVO.parseToBoxSignupInVO(signupInVO), restTemplate.getMessageConverters());
		MbackendResponseExtractor responseExtractor = new MbackendResponseExtractor(SignupOutVO.class,
				restTemplate.getMessageConverters());
		mbackendResponse = restTemplate.execute(BoxRoutes.BOX_ACCOUNT_CREATE, HttpMethod.POST, requestCallback,
				responseExtractor);

		if (mbackendResponse == null) {
			// FIXME: need to log this
			System.out.println("MbackendResponse is null");
			throw new IllegalArgumentException("REST response is null");
		}
		if (!mbackendResponse.isFoundError()) {
			SignupOutVO results = (SignupOutVO) mbackendResponse.getResponse();
			return results;
		} else {
			// need to convert RestError back into exception and throw it
			RestError restError = mbackendResponse.getErrResponse();
			BaseException ex = restError.transformRestError();
			// can do i18n here, for now just go with default
			ex.setMessage(ex.getDebugMessage());
			throw ex;
		}
	}

	@Override
	public String clientLogin(String xBBSession) throws MbackendException {
		/*
		 * logger.info("user service impl running!");
		 * 
		 * requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		 * requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		 * requestHeaders.add("X-BB-SESSION", xBBSession);
		 * HttpEntity<MultiValueMap<String, String>> requestEntity = new
		 * HttpEntity<MultiValueMap<String, String>>(null, requestHeaders);
		 * 
		 * ResponseEntity<?> response = null; try { response =
		 * restTemplate.exchange(BoxRoutes.BOX_ACCOUNT_GET_INFO, HttpMethod.GET,
		 * requestEntity, String.class); } catch (RestClientException rex) {
		 * logger.error(rex.getMessage()); throw new
		 * MbackendException(ErrorCodeCustomerEnum.PERMISSION_ERROR); } Object
		 * BoxOutVO = null; if (response.getStatusCode() == HttpStatus.OK) {
		 * BoxOutVO = response.getBody(); } else {
		 * logger.error(response.getStatusCode().toString());
		 * logger.error(response.getBody().toString()); throw new
		 * MbackendException(ErrorCodeCustomerEnum.PERMISSION_ERROR,response.getBody
		 * ().toString()); } logger.info("user service impl end!"); if
		 * (BoxOutVO.toString().contains("LikeVideo")) { return "LikeVideo"; }
		 * else { return "registered"; }
		 */
		return "LikeVideo";

	}

}
