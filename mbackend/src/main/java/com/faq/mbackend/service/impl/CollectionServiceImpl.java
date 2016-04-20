package com.faq.mbackend.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.faq.mbackend.common.AppConfig;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;

import com.faq.mbackend.common.BoxRoutes;
import com.faq.mbackend.common.BoxStatus;
import com.faq.mbackend.service.base.MbackendRequestCallback;
import com.faq.mbackend.service.base.MbackendResponse;
import com.faq.mbackend.service.base.MbackendResponseExtractor;
import com.faq.mbackend.dto.in.BoxCreateCollectionInVO;
import com.faq.mbackend.dto.out.BoxCreateCollectionOutVO;
import com.faq.mbackend.dto.out.BoxRegisterCollectionOutVO;
import com.faq.mbackend.service.CollectionService;
import com.faq.mbackend.exception.BaseException;
import com.faq.mbackend.exception.ErrorCodeCustomerEnum;
import com.faq.mbackend.exception.MbackendException;
import com.faq.mbackend.exception.RestError;

@Service
public class CollectionServiceImpl extends BaseServiceImpl implements
		CollectionService {

	@Override
	public BoxRegisterCollectionOutVO create(
			BoxCreateCollectionInVO boxCreateCollectionInVO,String xBBSession,String xBBsessionClient)
			throws MbackendException {

		MbackendResponse MbackendResponse = null;

		Map<String, String> headerAttrs = new HashMap<String, String>();
		headerAttrs.put(AppConfig.X_BB_SESSION,xBBSession);
		headerAttrs.put(AppConfig.X_BOX_APPCODE,
				AppConfig.BOX_APPCODE);

		RequestCallback requestCallback = new MbackendRequestCallback(headerAttrs,
				boxCreateCollectionInVO, restTemplate.getMessageConverters());
		MbackendResponseExtractor responseExtractor = new MbackendResponseExtractor(
				BoxCreateCollectionOutVO.class,
				restTemplate.getMessageConverters());
		MbackendResponse = restTemplate.execute(BoxRoutes.BOX_COLLECTION_CREATE
				+ boxCreateCollectionInVO.getCollectionName(), HttpMethod.POST,
				requestCallback, responseExtractor);

		if (MbackendResponse == null) {
			// FIXME: need to log this
			System.out.println("MbackendResponse is null");
			throw new MbackendException(ErrorCodeCustomerEnum.UNKNOWN_ERROR,"REST response is null");
		}
		if (!MbackendResponse.isFoundError()) {
			BoxCreateCollectionOutVO results = (BoxCreateCollectionOutVO) MbackendResponse
					.getResponse();
			BoxRegisterCollectionOutVO boxRegisterCollectionOutVO = null;
			if (BoxStatus.CREATE_COLLECTION_OK.equals(results.getHttp_code())||(BoxStatus.RESULT_ERROR.equalsIgnoreCase(results.getResult())&&results.getMessage().contains("already exists"))) {

				boxRegisterCollectionOutVO = createForClientUser(boxCreateCollectionInVO, xBBsessionClient);
			}else{
				throw new MbackendException(ErrorCodeCustomerEnum.UNKNOWN_ERROR,results.getMessage());
			}
			return boxRegisterCollectionOutVO;
		} else {
			// need to convert RestError back into exception and throw it
			RestError restError = MbackendResponse.getErrResponse();
			BaseException ex = restError.transformRestError();
			// can do i18n here, for now just go with default
			ex.setMessage(ex.getDebugMessage());
			throw ex;
		}

	}

	private BoxRegisterCollectionOutVO createForClientUser(
			BoxCreateCollectionInVO boxCreateCollectionInVO,String xBBsessionClient)
			throws MbackendException {
		MbackendResponse MbackendResponse = null;
		Map<String, String> headerAttrs = new HashMap<String, String>();
		headerAttrs.put(AppConfig.X_BB_SESSION, xBBsessionClient);
		headerAttrs.put(AppConfig.X_BOX_APPCODE,
				AppConfig.BOX_APPCODE);

		RequestCallback requestCallback = new MbackendRequestCallback(headerAttrs,
				boxCreateCollectionInVO, restTemplate.getMessageConverters());
		MbackendResponseExtractor responseExtractor = new MbackendResponseExtractor(
				BoxRegisterCollectionOutVO.class,
				restTemplate.getMessageConverters());
		MbackendResponse = restTemplate.execute(BoxRoutes.BOX_DATA_WRITE_MBACKEND_DOCUMENT, HttpMethod.POST,
				requestCallback, responseExtractor);

		if (MbackendResponse == null) {
			// FIXME: need to log this
			System.out.println("MbackendResponse is null");
			throw new IllegalArgumentException("REST response is null");
		}
		if (!MbackendResponse.isFoundError()) {
			BoxRegisterCollectionOutVO results = (BoxRegisterCollectionOutVO) MbackendResponse
					.getResponse();
			return results;
		} else {
			// need to convert RestError back into exception and throw it
			RestError restError = MbackendResponse.getErrResponse();
			BaseException ex = restError.transformRestError();
			// can do i18n here, for now just go with default
			ex.setMessage(ex.getDebugMessage());
			throw ex;
		}
	};

}
