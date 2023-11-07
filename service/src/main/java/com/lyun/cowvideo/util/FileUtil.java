package com.lyun.cowvideo.util;

import java.util.Arrays;

public class FileUtil {

    // 允许的视频类型
    private static final String[] VIDEO_FILTER = {"mp4", "mov", "m4v", "avi", "mkv", "flv", "wmv"};
    // 允许的图片类型
    private static final String[] IMG_FILTER = {"jpg", "png", "bmp"};

    public static boolean filterVideo(String fileName) {
        String lowFileName = fileName.toLowerCase();
        String[] nameSpilt = lowFileName.split("\\.");
        if (nameSpilt.length < 2) return false;
        return Arrays.stream(VIDEO_FILTER).anyMatch(f -> f.equals(nameSpilt[nameSpilt.length - 1]));
    }


    public static boolean filterImg(String fileName) {
        String lowFileName = fileName.toLowerCase();
        String[] nameSpilt = lowFileName.split("\\.");
        if (nameSpilt.length < 2) return false;
        return Arrays.stream(IMG_FILTER).anyMatch(f -> f.equals(nameSpilt[nameSpilt.length - 1]));
    }
}
