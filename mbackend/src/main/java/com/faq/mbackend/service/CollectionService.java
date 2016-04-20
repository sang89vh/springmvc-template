package com.faq.mbackend.service;

import com.faq.mbackend.dto.in.BoxCreateCollectionInVO;
import com.faq.mbackend.dto.out.BoxRegisterCollectionOutVO;
import com.faq.mbackend.exception.MbackendException;

public interface CollectionService {
	public BoxRegisterCollectionOutVO create(BoxCreateCollectionInVO boxCreateCollectionInVO,String xBBSession,String xBBSessionClient)
			throws MbackendException;
	
}
