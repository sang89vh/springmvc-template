package com.faq.mbackend.aspect;

import com.faq.mbackend.dto.in.BaseParseInVO;
import com.faq.mbackend.dto.in.MakeEffectInVO;
import java.util.Arrays;

import com.faq.mbackend.exception.MbackendException;
import com.faq.mbackend.service.ParseUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * Created by jack on 4/2/16.
 */

@Aspect
public class ControllerAspect {
    public final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    @Autowired
    private ParseUserService parseUserService;

//    @Before("allMethodsControlerPointcut()")
//    public void allServiceMethodsAdvice() {
//        logger.info("Before executing controller method");
//    }
//
//    //Pointcut to execute on all the methods of classes in a package
//    @Pointcut("within(com.faq.mbackend.controller.DubController.*)")
//    public void allMethodsControlerPointcut() {
//    }

    @Before("execution(* com.faq.mbackend.controller.DubController.*(*)) && args(baseParseInVO)")
    public void loggingAdvice(JoinPoint joinPoint,BaseParseInVO baseParseInVO) throws MbackendException{
        System.out.println("Before running loggingAdvice on method="+joinPoint.toString());

        System.out.println("ControllerAspect:Agruments Passed token=" + baseParseInVO.getToken());

             /* 0. check token baasbox */
        String token = baseParseInVO.getToken();
        parseUserService.getParseSession(token);
    }



}
