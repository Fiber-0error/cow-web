package com.lyun.cowvideo.util;

public class TimeUtil {

    public static String second2Time(long second) {
        long hour = second / 3600;
        long minute = (second - hour * 3600) / 60;
        long _second = second % 60;
        return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (_second < 10 ? "0" + _second : _second);

    }
}
