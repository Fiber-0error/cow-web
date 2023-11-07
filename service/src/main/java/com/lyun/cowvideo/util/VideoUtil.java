package com.lyun.cowvideo.util;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class VideoUtil {

    /**
     * 获取视频流里指定百分比位置的帧图片
     * @param videoStream 视频流
     * @param location 位置0-100
     * @return 截取的图片
     */
    public BufferedImage getBufferedImage(InputStream videoStream, int location) {
        if (location < 0 || location > 100) {
            return null;
        }
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videoStream);
        try {
            ff.start();
            long videoTime = ff.getLengthInTime() / 1000;// 视频秒数
            double frameRate = ff.getFrameRate();
            long targetTime = videoTime * location / 100;
            int targetFrameNumber = (int) (targetTime * frameRate);
            ff.setTimestamp(targetFrameNumber);
            Frame frame = ff.grabKeyFrame();

            // 截取的帧图片
            Java2DFrameConverter converter = new Java2DFrameConverter();
            BufferedImage srcImage = converter.getBufferedImage(frame);
            int srcImageWidth = srcImage.getWidth();
            int srcImageHeight = srcImage.getHeight();

            // 对截图进行等比例缩放(缩略图)
            int width = (int) (srcImageWidth * 0.5);
            int height = (int) (((double) width / srcImageWidth) * srcImageHeight);
            BufferedImage thumbnailImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            thumbnailImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            ff.stop();
            return thumbnailImage;
        } catch (FrameGrabber.Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取视频的秒数
     */
    public long getVideoTime(InputStream videoStream){
        FFmpegFrameGrabber ff = new FFmpegFrameGrabber(videoStream);
        try {
            ff.start();
            long second = ff.getLengthInTime() / 1000;
            ff.stop();
            return second;
        } catch (FrameGrabber.Exception e) {
            throw new RuntimeException(e);
        }
    }
}
