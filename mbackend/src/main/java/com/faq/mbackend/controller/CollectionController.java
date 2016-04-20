package com.faq.mbackend.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faq.mbackend.common.Utils;
import com.faq.mbackend.dto.in.BoxCreateCollectionInVO;
import com.faq.mbackend.dto.out.BoxRegisterCollectionOutVO;
import com.faq.mbackend.service.CollectionService;
import com.faq.mbackend.service.BaasboxUserService;
import com.faq.mbackend.exception.ErrorCodeCustomerEnum;
import com.faq.mbackend.exception.MbackendException;

@Controller
public class CollectionController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CollectionService collectionService;
	@Autowired
	private BaasboxUserService userService;
	@Autowired
	HttpSession session;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/collection/create", method = RequestMethod.POST)
	public @ResponseBody BoxRegisterCollectionOutVO create(
			@RequestBody BoxCreateCollectionInVO boxCreateCollectionInVO) {
		
		boolean isValid=Utils.validateCollectionName(boxCreateCollectionInVO.getCollectionName());
		if(!isValid){
			throw new MbackendException(ErrorCodeCustomerEnum.INVALID_PARAMS,"Tên bảng dữ liệu không đúng!");
		}
		logger.info("CollectionController.create running");
		String BOX_SESSION=Utils.getBoxSessionCode(session, userService);
		String BOX_SESSION_CLIENT=Utils.getBoxSessionClientCode(session);
		logger.info("admin session {}",BOX_SESSION);
		logger.info("client session {}",BOX_SESSION_CLIENT);
		return collectionService.create(boxCreateCollectionInVO,BOX_SESSION,BOX_SESSION_CLIENT);
	}
	
	@RequestMapping(value = "/collection/my-collection/{collectionId}/{applicationId}/{collectionName}", method = RequestMethod.GET)
	public String myCollection(Model model
			,@PathVariable("collectionId") String collectionId
			,@PathVariable("applicationId") String applicationId
			,@PathVariable("collectionName") String collectionName
			) {
		if(Utils.getBoxSessionClientCode(session)==null){
			 return "redirect:/user/login";
		}


		model.addAttribute("collectionId", collectionId);
		model.addAttribute("applicationId", applicationId);
		model.addAttribute("collectionName", collectionName);
		
		return "collection/my-collection";
	}
	@RequestMapping(value = "/collection/my-document/{documentId}/{collectionId}/{applicationId}/{collectionName}", method = RequestMethod.GET)
	public String myDocument(Model model,@PathVariable("documentId") String documentId
			,@PathVariable("collectionId") String collectionId
			,@PathVariable("applicationId") String applicationId
			,@PathVariable("collectionName") String collectionName
			) {
		if(Utils.getBoxSessionClientCode(session)==null){
			return "redirect:/user/login";
		}
		
		
		model.addAttribute("documentId", documentId);
		model.addAttribute("collectionId", collectionId);
		model.addAttribute("applicationId", applicationId);
		model.addAttribute("collectionName", collectionName);
		
		return "collection/my-document";
	}

}
