package com.faq.mbackend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.faq.mbackend.service.BaasboxUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LikeVideosController extends BaseController {
	@Autowired
	private BaasboxUserService userService;

	@Autowired
	HttpSession session;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "videos/manager", method = RequestMethod.GET)
	public String manager(Model model) {
		logger.info("manager");

		

		return "videos/manager";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "videos/create", method = RequestMethod.GET)
	public String create( Model model) {
		logger.info("create");

		

		return "videos/create";
	}

	@RequestMapping(value = "videos/manager-singer", method = RequestMethod.GET)
	public String managerSinger(Model model) {
		logger.info("manager-singer");



		return "videos/manager-singer";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "videos/create-singer", method = RequestMethod.GET)
	public String createSinger( Model model) {
		logger.info("create-singer");



		return "videos/create-singer";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "videos/detail/{videoId}", method = RequestMethod.GET)
	public String detail( Model model,@PathVariable("videoId") String videoId) {
		
		logger.info("videoId {}",videoId);
		model.addAttribute("VIDEOID", videoId);

		

		return "videos/detail";
	}/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "videos/detail-singer/{singerId}", method = RequestMethod.GET)
	public String detailSinger( Model model,@PathVariable("singerId") String singerId) {

		logger.info("singerId {}",singerId);
		model.addAttribute("SINGERID", singerId);



		return "videos/detail-singer";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "videos/vimeo-detail/{videoId}", method = RequestMethod.GET)
	public String vimeoDetail( Model model,@PathVariable("videoId") String videoId) {
		
		logger.info("videoId {}",videoId);
		model.addAttribute("VIDEOID", videoId);

		

		return "videos/vimeo-detail";
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "videos/{videoId}/{id}", method = RequestMethod.GET)
	public String vimeo( Model model,@PathVariable("videoId") String videoId,
			@PathVariable("id") String id
			) {
		
		logger.info("videoId {}",videoId);
		model.addAttribute("VIDEOID", videoId);
		model.addAttribute("ID", id);

		

		return "videos/vimeo";
	}

}
