package com.faq.mbackend.service;

import com.faq.mbackend.dto.in.BoxLoginInVO;
import com.faq.mbackend.dto.in.SignupInVO;
import com.faq.mbackend.dto.out.BoxLoginOutVO;
import com.faq.mbackend.dto.out.SignupOutVO;
import com.faq.mbackend.exception.MbackendException;

public interface BaasboxUserService {
	public BoxLoginOutVO login(BoxLoginInVO BoxLoginInVO)
			throws MbackendException;

	public SignupOutVO signup(SignupInVO signupInVO,String xBBSession) throws MbackendException;
	public String clientLogin(String xBBSession) throws MbackendException;
}
