package com.faq.mbackend.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faq.mbackend.exception.BaseException;
import com.faq.mbackend.exception.RestError;

public class BaseController {
    /**
     * Logger available to subclasses
     */

    public final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BaseException.class)
    public
    @ResponseBody
    RestError handleCustomException(BaseException ex,
                                    HttpServletResponse response) {
        logger.info("handle controller exception");
        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return ex
                .transformException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
