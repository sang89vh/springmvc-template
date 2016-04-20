package com.faq.mbackend.service;

/**
 * Created by jack on 4/2/16.
 */

import com.faq.mbackend.dto.out.ParseSessionOutVO;
import com.faq.mbackend.exception.MbackendException;

public interface ParseUserService {

    public ParseSessionOutVO getParseSession(String parseToken) throws MbackendException;
}
