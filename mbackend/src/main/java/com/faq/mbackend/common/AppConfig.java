package com.faq.mbackend.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    public static final String EFFECT_BW = "BW";
    public static final String X_PARSE_SESSION_TOKEN = "X-Parse-Session-Token";


    public static boolean IS_LOGGING_FFMPEG;

    @Value("${is_logging_ffmpeg}")
    public void setIsLoggingFfmpeg(String isLoggingFfmpeg) {
        IS_LOGGING_FFMPEG = Boolean.parseBoolean(isLoggingFfmpeg);
    }

    public static String BOX_USERNAME;

    @Value("${box_username}")
    public void setBoxUsername(String boxUsername) {
        BOX_USERNAME = boxUsername;
    }

    public static String BOX_PASSWORD;

    @Value("${box_password}")
    public void setBoxPassword(String boxPassword) {
        BOX_PASSWORD = boxPassword;
    }

    public static String BOX_APPCODE;

    @Value("${box_appcode}")
    public void setBoxAppcode(String boxAppcode) {
        BOX_APPCODE = boxAppcode;
    }


    public static String X_BOX_APPCODE;

    @Value("${x_box_appcode}")
    public void setxBoxAppcode(String xBoxAppcode) {
        X_BOX_APPCODE = xBoxAppcode;

    }

    public static String X_BB_SESSION;

    @Value("${x_bb_session}")
    public void setxBbSession(String xBbSession) {
        X_BB_SESSION = xBbSession;
    }

    //key session user login

    public static String SESSION_BOX_USER;

    @Value("${session_box_user}")
    public void setSessionBoxUser(String sessionBoxUser) {
        SESSION_BOX_USER = sessionBoxUser;
    }


    public static String SESSION_BOX_USER_CLIENT;

    @Value("${session_box_user_client}")
    public void setSessionBoxUserClient(String sessionBoxUserClient) {
        SESSION_BOX_USER_CLIENT = sessionBoxUserClient;
    }

	/* Parse-server infomation */


    public static String PARSE_APP_ID;

    @Value("${parse_app_id}")
    public void setParseAppId(String parseAppId) {
        PARSE_APP_ID = parseAppId;
    }


    public static String PARSE_API_KEY;

    @Value("${parse_api_key}")
    public void setParseApiKey(String parseApiKey) {
        PARSE_API_KEY = parseApiKey;
    }


    public static String PARSE_MASTER_KEY;

    @Value("${parse_master_key}")
    public void setParseMasterKey(String parseMasterKey) {
        PARSE_MASTER_KEY = parseMasterKey;
    }

    public static String PARSE_URL;

    @Value("${parse_url}")
    public void setParseUrl(String parseUrl) {
        PARSE_URL = parseUrl;
    }


    public static String PATH_IMAGES;

    @Value("${path_images}")
    public void setPathImages(String pathImages) {
        PATH_IMAGES = pathImages;
    }

    public static String PATH_GIFS;

    @Value("${path_gifs}")
    public void setPathGifs(String pathGifs) {
        PATH_GIFS = pathGifs;
    }

    public static String PATH_VIDEOS;

    @Value("${path_videos}")
    public void setPathVideos(String pathVideos) {
        PATH_VIDEOS = pathVideos;
    }


    public static String PATH_AUDIOS;

    @Value("${path_audios}")
    public void setPathAudios(String pathAudios) {
        PATH_AUDIOS = pathAudios;
    }

    public static String PATH_NAVTIVE_VIDEOS;

    @Value("${path_navtive_videos}")
    public void setPathNavtiveVideos(String pathNavtiveVideos) {
        PATH_NAVTIVE_VIDEOS = pathNavtiveVideos;
    }

    public static String PATH_SOUNDWAVE;

    @Value("${path_soundwave}")
    public void setPathSoundwave(String pathSoundwave) {
        PATH_SOUNDWAVE = pathSoundwave;
    }


    public static String DUB_VIDEO;

    @Value("${dub_video}")
    public void setDubVideo(String dubVideo) {
        DUB_VIDEO = dubVideo;
    }


    public static String X_PARSE_APPLICATION_ID;

    @Value("${x_parse_application_id}")
    public void setxParseApplicationId(String xParseApplicationId) {
        X_PARSE_APPLICATION_ID = xParseApplicationId;
    }


    public static String X_PARSE_REST_API_KEY;

    @Value("${x_parse_rest_api_key}")
    public void setxParseRestApiKey(String xParseRestApiKey) {
        X_PARSE_REST_API_KEY = xParseRestApiKey;
    }


    public static String PATH_THUMB;

    @Value("${path_thumb}")
    public void setPathThumb(String pathThumb) {
        PATH_THUMB = pathThumb;
    }


    public static String PATH_VIDEOS_EFFECT;

    @Value("${path_videos_effect}")
    public void setPathVideosEffect(String pathVideosEffect) {
        PATH_VIDEOS_EFFECT = pathVideosEffect;
    }

    public static String PATH_MP3_CONVERTED;

    @Value("${path_mp3_converted}")
    public void setPathMp3Converted(String pathMp3Converted) {
        PATH_MP3_CONVERTED = pathMp3Converted;
    }


}
