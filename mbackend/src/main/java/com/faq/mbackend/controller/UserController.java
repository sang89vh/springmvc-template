package com.faq.mbackend.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faq.mbackend.common.AppConfig;
import com.faq.mbackend.common.Utils;
import com.faq.mbackend.dto.in.BoxStoreUserClientInVO;
import com.faq.mbackend.dto.in.SignupInVO;
import com.faq.mbackend.dto.out.SignupOutVO;
import com.faq.mbackend.service.BaasboxUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController extends BaseController {
	@Autowired
	private BaasboxUserService userService;

	@Autowired
	HttpSession session;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/user/signup", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "user/signup";
	}
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("parse:{}", AppConfig.PARSE_URL);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "user/login";
	}

	@RequestMapping(value = "/user/signup", method = RequestMethod.POST)
	public @ResponseBody SignupOutVO signup(@RequestBody SignupInVO signupInVO) {
		logger.info("email:{}", signupInVO.getEmail());
		logger.info("password:{}", signupInVO.getPassword());
		logger.info("application:{}", signupInVO.getApplication());
		logger.info("application:{}", signupInVO.getApplication());
		String BOX_SESSION=Utils.getBoxSessionCode(session, userService);
		logger.info(BOX_SESSION);
		SignupOutVO signupOutVO = userService.signup(signupInVO,BOX_SESSION);

		
		
		return signupOutVO;
	}
	@RequestMapping(value = "/user/store-client-user", method = RequestMethod.POST)
	public @ResponseBody Integer storeClientUser(@RequestBody BoxStoreUserClientInVO boxStoreUserClientInVO) {
		logger.info("name:{}", boxStoreUserClientInVO.getUsername());
		logger.info("session:{}", boxStoreUserClientInVO.getToken());
		
		String role=userService.clientLogin(boxStoreUserClientInVO.getToken());
		boxStoreUserClientInVO.setRole(role);
		session.setAttribute(AppConfig.SESSION_BOX_USER_CLIENT, boxStoreUserClientInVO);
		
		
		
		return 200;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		session.removeAttribute(AppConfig.SESSION_BOX_USER_CLIENT);
		return "redirect:/user/login";
	}

}
