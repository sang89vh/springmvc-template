package com.faq.mbackend.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.faq.mbackend.common.AppConfig;
import com.faq.mbackend.common.FfmpegUtil;
import com.faq.mbackend.dto.in.MakeEffectInVO;
import com.faq.mbackend.dto.out.*;
import com.faq.mbackend.service.DubVideoService;
import com.faq.mbackend.exception.ErrorCodeCustomerEnum;
import com.faq.mbackend.exception.MbackendException;
import com.faq.mbackend.service.ParseUserService;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import com.faq.mbackend.common.Utils;
import com.faq.mbackend.dto.in.DubMakeVideoInVO;
import com.faq.mbackend.dto.in.DubUploadAudioInVO;
import com.faq.mbackend.dto.in.DubUploadVideoInVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "dub")
public class DubController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(DubController.class);

    @Autowired
    private DubVideoService dubVideoService;


    @Autowired
    private ParseUserService parseUserService;


    @RequestMapping(value = "make_video", method = RequestMethod.POST)
    public
    @ResponseBody
    DubMakeVideoOutVO makeVideo(@RequestBody DubMakeVideoInVO makeVideoInVO) {

		/**/

        return null;
    }

    @RequestMapping(value = "upload_video", method = RequestMethod.PUT)
    public
    @ResponseBody
    DubUploadVideoOutVO uploadVideo(@RequestBody DubUploadVideoInVO uploadVideoInVO) {

		/* 0. check token baasbox */

		/* 1. Save file to dub/native_audio */

		/* 2. Save information as ouput to baasbox */

        return null;
    }


    @RequestMapping(value = "make-effect", method = RequestMethod.POST)
    public
    @ResponseBody
    DubUploadVideoOutVO makeEffect(@RequestBody MakeEffectInVO makeEffectInVO) {


        logger.info("begin make effect");
        logger.info("Effect code:" + makeEffectInVO.getEffectCode());
        logger.info("url video:" + makeEffectInVO.getUrl());
//		/* 0. check token baasbox */
//		String token = makeEffectInVO.getToken();
//		ParseSessionOutVO parseSessionOutVO = parseUserService.getParseSession(token);
//		logger.info("token:{}", parseSessionOutVO.getSessionToken());
        /* 1. Convert and Save file to dub/videos_effect */
        String effectCode = makeEffectInVO.getEffectCode();

        switch (effectCode) {
            case AppConfig.EFFECT_BW:
                FfmpegUtil.makeEffectBlackWhite(makeEffectInVO.getUrl());
                break;
            default:
                throw new MbackendException(ErrorCodeCustomerEnum.INVALID_PARAMS,
                        "Can't found effect code is:" + effectCode);

        }

		/* 2. Save information as ouput to parse */
        DubUploadVideoOutVO dubUploadVideoOutVO = dubVideoService.updateVideo(makeEffectInVO);

        logger.info("end make effect");

        return dubUploadVideoOutVO;
    }

    @RequestMapping(value = "upload_audio", method = RequestMethod.PUT)
    public
    @ResponseBody
    DubUploadAudioOutVO uploadAudio(@RequestBody DubUploadAudioInVO uploadAudioInVO)
            throws IOException {

        logger.info("uploadAudio");
		/* 0. check token baasbox */

		/* 1. Save file to dub/native_audio */
        String content = uploadAudioInVO.getContent();
        byte data[] = Base64.decodeBase64(content);
        Utils.writeMP3File(data, uploadAudioInVO.getUserId() + Utils.generateNameFile(".mp3"));

		/* 2. Save information as ouput to baasbox */
        DubUploadAudioOutVO dubUploadAudioOutVO = new DubUploadAudioOutVO();

        return dubUploadAudioOutVO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "upload")
    public String provideUploadInfo(Model model) {
        File rootFolder = new File(AppConfig.PATH_NAVTIVE_VIDEOS);
        List<String> fileNames = Arrays.stream(rootFolder.listFiles()).map(f -> f.getName())
                .collect(Collectors.toList());

        model.addAttribute("files",
                Arrays.stream(rootFolder.listFiles()).sorted(Comparator.comparingLong(f -> -1 * f.lastModified()))
                        .map(f -> f.getName()).collect(Collectors.toList()));

        return "dub/uploadForm";
    }

    @RequestMapping(method = RequestMethod.POST, value = "upload")
    public
    @ResponseBody
    DubUploadVideoOutVO handleFileUpload(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "audioId", required = false) String audioId,
            @RequestParam(value = "url", required = false) String audioUrl,
            @RequestParam(value = "uploaded_file", required = true) MultipartFile file) {

        logger.info("upload file begin");
        logger.info("user id:" + userId);
        logger.info("token:" + token);
        logger.info("audio id:" + audioId);
        logger.info("audio url:" + audioUrl);

        DubUploadVideoOutVO dubUploadVideoOutVO = new DubUploadVideoOutVO();

        String fileName = Utils.generateNameFile(".mp4");

        logger.info("PATH_NATIVE_VIDEOS:" + AppConfig.PATH_NAVTIVE_VIDEOS);

        File fileTmp = new File(AppConfig.PATH_NAVTIVE_VIDEOS + userId);
        if (!fileTmp.exists()) {
            fileTmp.mkdirs();

        }

        String haftPathFile = userId + "/" + fileName;
        String fullPathfile = AppConfig.PATH_NAVTIVE_VIDEOS + haftPathFile;

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPathfile)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();

                File filefull = new File(fullPathfile);
                if (!filefull.exists()) {
                    filefull.mkdirs();

                }

                FfmpegUtil.editVideo(haftPathFile, audioUrl, haftPathFile);

                dubUploadVideoOutVO.setUrl(haftPathFile);

                DubMakeVideoInVO dubMakeVideoInVO = new DubMakeVideoInVO();

                dubMakeVideoInVO.setUrl(haftPathFile);

                dubMakeVideoInVO.setThumb(haftPathFile.replace(".mp4", ".png"));

                dubMakeVideoInVO.setUserId(userId);

                // store video to parse server
                ParseBaseOutVO parseBaseOutVO = dubVideoService.saveVideo(dubMakeVideoInVO);
                dubUploadVideoOutVO.setVideoId(parseBaseOutVO.getObjectId());

            } catch (Exception e) {

                throw new MbackendException(ErrorCodeCustomerEnum.INVALID_PARAMS,
                        "You failed to upload " + haftPathFile + " => " + e.getMessage());

            }
        } else {
            throw new MbackendException(ErrorCodeCustomerEnum.INVALID_PARAMS,
                    "You failed to upload,because the file was empty");
        }

        logger.info("upload file end:" + fullPathfile);

        return dubUploadVideoOutVO;
    }

    @RequestMapping(method = RequestMethod.POST, value = "convert2mp3")
    public
    @ResponseBody
    DubConvertToMp3OutVO convert2mp3(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "extension", required = false) String extension,
            @RequestParam(value = "uploaded_file", required = true) MultipartFile file) {

        logger.info("upload file begin");
        logger.info("token:" + token);
        logger.info("extension:" + extension);

        String userId = "convert2mp3";

        DubConvertToMp3OutVO convertToMp3OutVO = new DubConvertToMp3OutVO();

        String fileName = Utils.generateNameFile("." + extension);

        logger.info("PATH_NATIVE_VIDEOS:" + AppConfig.PATH_NAVTIVE_VIDEOS);

        File fileTmp = new File(AppConfig.PATH_NAVTIVE_VIDEOS + userId);
        if (!fileTmp.exists()) {
            fileTmp.mkdirs();

        }

        String haftPathFile = userId + "/" + fileName;
        String fullPathfile = AppConfig.PATH_NAVTIVE_VIDEOS + haftPathFile;

        if (!file.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPathfile)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();

                File filefull = new File(fullPathfile);
                if (!filefull.exists()) {
                    filefull.mkdirs();

                }

                FfmpegUtil.convertToMP3(haftPathFile, extension);

                convertToMp3OutVO.setUrl(haftPathFile);


            } catch (Exception e) {

                throw new MbackendException(ErrorCodeCustomerEnum.INVALID_PARAMS,
                        "You failed to upload " + haftPathFile + " => " + e.getMessage());

            }
        } else {
            throw new MbackendException(ErrorCodeCustomerEnum.INVALID_PARAMS,
                    "You failed to upload,because the file was empty");
        }

        logger.info("upload file end:" + fullPathfile);

        return convertToMp3OutVO;
    }

}
