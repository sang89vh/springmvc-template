package com.faq.mbackend.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faq.mbackend.common.Utils;
import com.faq.mbackend.dto.in.SignupInVO;
import com.faq.mbackend.dto.out.SignupOutVO;
import com.faq.mbackend.service.BaasboxUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ApplicationController extends BaseController {
	@Autowired
	private BaasboxUserService userService;

	@Autowired
	HttpSession session;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/application/my-application", method = RequestMethod.GET)
	public String myApplication(Locale locale, Model model) {
		if(Utils.getBoxSessionClientCode(session)==null){
			 return "redirect:/user/login";
		}

		return "application/my-application";
	}
	@RequestMapping(value = "/application/profile/{applicationId}", method = RequestMethod.GET)
	public String profile( Model model,@PathVariable("applicationId") String applicationId) {
		if(Utils.getBoxSessionClientCode(session)==null){
			 return "redirect:/user/login";
		}


		model.addAttribute("applicationId", applicationId);
		
		return "application/profile";
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/application/manager/{applicationId}", method = RequestMethod.GET)
	public String manager(Model model,@PathVariable("applicationId") String applicationId) {
		if(Utils.getBoxSessionClientCode(session)==null){
			 return "redirect:/user/login";
		}

		logger.info("applicationId is {}.", applicationId);	
		
		model.addAttribute("applicationId", applicationId);
		
		return "application/manager";
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/application/file/{applicationId}", method = RequestMethod.GET)
	public String file(Model model,@PathVariable("applicationId") String applicationId) {
		if(Utils.getBoxSessionClientCode(session)==null){
			 return "redirect:/user/login";
		}

		logger.info("applicationId is {}.", applicationId);	
		
		model.addAttribute("applicationId", applicationId);
		
		return "application/file";
	}

	@RequestMapping(value = "/application/my-application", method = RequestMethod.POST)
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

}
