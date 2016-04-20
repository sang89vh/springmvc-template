package com.faq.mbackend.common;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faq.mbackend.exception.ErrorCodeCustomerEnum;
import com.faq.mbackend.exception.MbackendException;

public class FfmpegUtil {
    public static final Logger logger = LoggerFactory.getLogger(FfmpegUtil.class);

    /**
     * @param pathInputMP4 userid/xyz.mp4
     * @param pathInputMp3 dub.mp3
     * @param pathOutput   userid/abc.mp4
     * @return
     * @throws MbackendException
     */
    public static String editVideo(String pathInputMP4, String pathInputMp3, String pathOutput) throws MbackendException {

        logger.info("start edit video");
        final String pathFileInput = AppConfig.PATH_NAVTIVE_VIDEOS + pathInputMP4;

        /* Mbackend bussiness exception*/
        MbackendException mbex = new MbackendException(ErrorCodeCustomerEnum.UNKNOWN_ERROR, "Edit video fail");

		/*Building command call ffmpeg */
        StringBuffer cmd = new StringBuffer();
        cmd.append("ffmpeg -i ");
        cmd.append(pathFileInput);
        cmd.append(" -i ");
        cmd.append(AppConfig.PATH_AUDIOS);
        cmd.append(pathInputMp3);
        cmd.append(" -c copy -map 0:0 -map 1:0 -shortest ");

        StringBuffer pathOutFile = new StringBuffer(AppConfig.PATH_VIDEOS);
        pathOutFile.append(pathOutput);

        cmd.append(pathOutFile);

        logger.info("cmd:{}", cmd);

		/* create path output file if not exists */


        File file = new File(pathOutFile.toString());

        if (!file.getParentFile().exists()) {

            file.getParentFile().mkdirs();
        }

        try {

			
			/* Execute command with ffmpeg */
            Process prc = Runtime.getRuntime().exec(cmd.toString());

            /*write log */
            InputStream stderr = prc.getErrorStream();
            writeLogRuntimeCMD(stderr);

			/* Check status execute command */
            prc.waitFor();
            int exitCode = prc.exitValue();

			/* error when execute command */
            if (exitCode != 0) {

                logger.error("Convert video fail", mbex);

                throw mbex;
            }
        } catch (InterruptedException e) {

            logger.error("Convert video fail", e);

            throw mbex;

        } catch (IOException ioe) {

            logger.error("Convert video fail", ioe);

            throw mbex;
        }

        logger.info("Result edit video:{}", pathOutFile);
        changePermissionFolder(pathOutFile.toString());

//        boolean isRead = fileOut.setReadable(true, false);
//
//        logger.info("is read: {}", isRead);

        new Thread(new Runnable() {
            @Override
            public void run() {

                exportThumb(pathOutput);
                File fileInput = new File(pathFileInput);
                boolean isDelete = fileInput.delete();
                logger.info("is delete file input:{}", isDelete);


            }
        }).start();

        logger.info("End edit video");

        return pathOutFile.toString();
    }

    private static void changePermissionFolder(String pathOutFile) {
        File fileOut = new File(pathOutFile);


        String parentPath = fileOut.getParentFile().getAbsolutePath();

        try {
        /* Execute command  */
            Process prc = Runtime.getRuntime().exec("chmod -R 775 " + parentPath);

            /*write log */
            InputStream stderr = prc.getErrorStream();
            writeLogRuntimeCMD(stderr);

			/* Check status execute command */
            prc.waitFor();
            int exitCode = prc.exitValue();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        logger.info("parentPath is : {}", parentPath);
    }

    /**
     * @param halfPathFileMp3 audios/dub.mp3
     * @return
     */
    public static String exportSoundwaveImg(String halfPathFileMp3) {

        /* Mbackend bussiness exception*/
        MbackendException mbex = new MbackendException(ErrorCodeCustomerEnum.UNKNOWN_ERROR, "Export soundwave fail");

        String fullPathMp3 = AppConfig.PATH_AUDIOS + halfPathFileMp3;


        String halfPathFileImg = halfPathFileMp3.replace(".mp3", ".png");

        String pathFileImg = AppConfig.PATH_SOUNDWAVE + halfPathFileImg;

        File fileImg = new File(pathFileImg);

        if (!fileImg.getParentFile().exists()) {
            fileImg.getParentFile().mkdirs();
        }

        StringBuffer cmd = new StringBuffer("ffmpeg -i ");
        cmd.append(fullPathMp3);
        cmd.append(" -filter_complex \\ \"[0:a]showwavespic=s=300x100,colorchannelmixer=rr=102/255:gg=102/255:bb=102/255,");
        cmd.append("drawbox=x=(iw-w)/2:y=(ih-h)/2:w=iw:h=1:color=#ecf0f1,format=rgba,colorkey=black\" \\");
        cmd.append(" -vframes 1 ");
        cmd.append(pathFileImg);

        logger.info("cmd soundwave:{}", cmd);

        try {


			/* Execute command with ffmpeg */
            Process prc = Runtime.getRuntime().exec(cmd.toString());

            /*write log */
            InputStream stderr = prc.getErrorStream();
            writeLogRuntimeCMD(stderr);

            /* Check status execute command */
            prc.waitFor();
            int exitCode = prc.exitValue();

			/* error when execute command */
            if (exitCode != 0) {

                logger.error("Export soundwave fail", mbex);

                throw mbex;
            }
        } catch (InterruptedException e) {

            logger.error("Export soundwave fail", e);

            throw mbex;

        } catch (IOException ioe) {

            logger.error("Export soundwave fail", ioe);

            throw mbex;
        }

        logger.info("Result edit video:{}", pathFileImg);

        changePermissionFolder(pathFileImg);

        logger.info("End export soundwave");

        return halfPathFileImg;
    }


    public static String exportThumb(String halfPathFileMp4) {
         /* Mbackend bussiness exception*/
        MbackendException mbex = new MbackendException(ErrorCodeCustomerEnum.UNKNOWN_ERROR, "Export soundwave fail");

        String fullPathMp4 = AppConfig.PATH_VIDEOS + halfPathFileMp4;


        String halfPathFileImg = halfPathFileMp4.replace(".mp4", ".png");

        String pathFileImg = AppConfig.PATH_THUMB + halfPathFileImg;

        File fileImg = new File(pathFileImg);

        if (!fileImg.getParentFile().exists()) {
            fileImg.getParentFile().mkdirs();
        }

        StringBuffer cmd = new StringBuffer("ffmpeg -i ");


        cmd.append(fullPathMp4);
        cmd.append("  -ss 00:00:3.435 -vf scale=120:160 -vframes 1 ");
        cmd.append(pathFileImg);

        logger.info("cmd thumbs:{}", cmd);

        try {


			/* Execute command with ffmpeg */
            Process prc = Runtime.getRuntime().exec(cmd.toString());

            /*write log */
            InputStream stderr = prc.getErrorStream();
            writeLogRuntimeCMD(stderr);

			/* Check status execute command */
            prc.waitFor();
            int exitCode = prc.exitValue();

			/* error when execute command */
            if (exitCode != 0) {

                logger.error("Export thumbs fail", mbex);

                throw mbex;
            }
        } catch (InterruptedException e) {

            logger.error("Export thumbs fail", e);

            throw mbex;

        } catch (IOException ioe) {

            logger.error("Export thumbs fail", ioe);

            throw mbex;
        }

        logger.info("Result thumbs:{}", pathFileImg);
        changePermissionFolder(pathFileImg);


        logger.info("End export thumbs");

        return halfPathFileImg;
    }


    public static String makeEffectBlackWhite(String halfPathVideo) {

        logger.info("begin convert blackwhite");

        /* Mbackend bussiness exception*/
        MbackendException mbex = new MbackendException(ErrorCodeCustomerEnum.UNKNOWN_ERROR, "Edit video fail");

        String halfPathVideoEdited = halfPathVideo;
        String fullPathVideo = AppConfig.PATH_VIDEOS + halfPathVideo;
        String fullPathVideoEdited = AppConfig.PATH_VIDEOS_EFFECT + halfPathVideoEdited;

        StringBuffer cmd = new StringBuffer("ffmpeg -i ");
        cmd.append(fullPathVideo);
        cmd.append(" -vf yadif=0:0:0,hue=s=0  -strict -2 ");
//        cmd.append(" -vf yadif=0:0:0,hflip,hue=s=0  -strict -2 ");
        cmd.append(fullPathVideoEdited);

        logger.info("fullPathVideoEdited:" + fullPathVideoEdited);

        File file = new File(fullPathVideoEdited);

        if (file.exists()) {

            file.delete();

        } else if (!file.getParentFile().exists()) {

            file.getParentFile().mkdirs();
        }

        logger.info("CMD make effect BW:{}", cmd.toString());
        try {

			/* Execute command with ffmpeg */
            Process prc = Runtime.getRuntime().exec(cmd.toString());

            /*write log */
            InputStream stderr = prc.getErrorStream();
            writeLogRuntimeCMD(stderr);
            /* Check status execute command */
            prc.waitFor();
            int exitCode = prc.exitValue();


			/* error when execute command */
            if (exitCode != 0) {

                logger.error("ExitCode!=0:edit blackwhite fail:{}", mbex.getMessage(), mbex);

                throw mbex;
            }
        } catch (InterruptedException e) {

            logger.error("InterruptedException-edit blackwhite fail:{}", e.getMessage(), e);

            throw mbex;

        } catch (IOException ioe) {

            logger.error("IOException-edit blackwhite fail:{}", ioe.getMessage(), ioe);

            throw mbex;
        }

        logger.info("Result blackwhite:{}", fullPathVideoEdited);

        changePermissionFolder(fullPathVideoEdited);


        logger.info("End convert blackwhite");

        return halfPathVideoEdited;
    }

    public static String convertToMP3(String halfPathVideo,String extentsionFile) {

        logger.info("begin convert to mp3");

        /* Mbackend bussiness exception*/
        MbackendException mbex = new MbackendException(ErrorCodeCustomerEnum.UNKNOWN_ERROR, "Convert video fail");

        String halfPathVideoEdited = halfPathVideo;
        String fullPathVideo = AppConfig.PATH_VIDEOS + halfPathVideo;
        String fullPathVideoEdited = AppConfig.PATH_MP3_CONVERTED + halfPathVideoEdited.replace("."+extentsionFile, ".mp3");;

        StringBuffer cmd = new StringBuffer("ffmpeg -i ");
        cmd.append(fullPathVideo);
        cmd.append(" -q:a 0 -map a  ");
        cmd.append(fullPathVideoEdited);

        logger.info("fullPathVideoEdited:" + fullPathVideoEdited);

        File file = new File(fullPathVideoEdited);

        if (file.exists()) {

            file.delete();

        } else if (!file.getParentFile().exists()) {

            file.getParentFile().mkdirs();
        }

        logger.info("CMD convert to mp3:{}", cmd.toString());
        try {

			/* Execute command with ffmpeg */
            Process prc = Runtime.getRuntime().exec(cmd.toString());

            /*write log */
            InputStream stderr = prc.getErrorStream();
            writeLogRuntimeCMD(stderr);
            /* Check status execute command */
            prc.waitFor();
            int exitCode = prc.exitValue();


			/* error when execute command */
            if (exitCode != 0) {

                logger.error("ExitCode!=0:convert to mp3 fail:{}", mbex.getMessage(), mbex);

                throw mbex;
            }
        } catch (InterruptedException e) {

            logger.error("InterruptedException-convert to mp3 fail:{}", e.getMessage(), e);

            throw mbex;

        } catch (IOException ioe) {

            logger.error("IOException-convert to mp3 fail:{}", ioe.getMessage(), ioe);

            throw mbex;
        }

        logger.info("Result convert to mp3:{}", fullPathVideoEdited);

        changePermissionFolder(fullPathVideoEdited);


        logger.info("End convert to mp3");

        return halfPathVideoEdited;
    }

    private static void writeLogRuntimeCMD(InputStream stderr) throws IOException {

        if(AppConfig.IS_LOGGING_FFMPEG) {
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            logger.info("<CMDLog>");
            while ((line = br.readLine()) != null) {
                logger.info(line);
            }
            logger.info("</CMDLog>");
        }

    }
}
