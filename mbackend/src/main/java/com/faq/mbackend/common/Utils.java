package com.faq.mbackend.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.faq.mbackend.dto.in.BoxLoginInVO;
import com.faq.mbackend.dto.in.BoxStoreUserClientInVO;
import com.faq.mbackend.dto.out.BoxLoginOutVO;
import com.faq.mbackend.service.BaasboxUserService;

public class Utils {
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);
	public static String getBoxSessionCode(HttpSession session,
			BaasboxUserService userService) {
		BoxLoginOutVO BoxLoginOutVO = getBoxSession(session, userService);
		return BoxLoginOutVO.getData().getX_BB_SESSION();
	}

	public static String getBoxSessionClientCode(HttpSession session) {
		BoxStoreUserClientInVO boxStoreUserClientInVO = getBoxSessionClient(session);
		if (boxStoreUserClientInVO != null) {
			return boxStoreUserClientInVO.getToken();
		} else {
			return null;
		}
	}

	public static BoxLoginOutVO getBoxSession(HttpSession session,
			BaasboxUserService userService) {
		BoxLoginOutVO BoxLoginOutVO = null;
		Object sessionObject = session
				.getAttribute(AppConfig.SESSION_BOX_USER);
		if (sessionObject != null) {
			BoxLoginOutVO = (BoxLoginOutVO) sessionObject;
		} else {
			BoxLoginOutVO = userService.login(new BoxLoginInVO());
			session.setAttribute(AppConfig.SESSION_BOX_USER,
					BoxLoginOutVO);
		}
		return BoxLoginOutVO;
	}

	public static BoxStoreUserClientInVO getBoxSessionClient(HttpSession session) {
		BoxStoreUserClientInVO boxStoreUserClientInVO = null;
		Object sessionObject = session
				.getAttribute(AppConfig.SESSION_BOX_USER_CLIENT);
		if (sessionObject != null) {
			boxStoreUserClientInVO = (BoxStoreUserClientInVO) sessionObject;
		}
		return boxStoreUserClientInVO;
	}

	public static boolean validateCollectionName(String collectionName) {
		String regex = "^[a-zA-Z0-9_]{2,35}$";
		// Create a Pattern object
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(collectionName);
		boolean valid = m.matches();
		return valid;
	}
	
	public static void writeMP3File(final byte[] data, final String filePath) throws IOException {

	    final File mp3File = new File(AppConfig.PATH_AUDIOS + File.separator + filePath);

	    mp3File.getParentFile().mkdirs(); // Ensure any parent directories are created
	    
	    logger.info(data.toString());
	    FileCopyUtils.copy(data, mp3File);
	}
	
	public static String  generateNameFile(String extension){
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		return timeStamp + extension;
	}
}
