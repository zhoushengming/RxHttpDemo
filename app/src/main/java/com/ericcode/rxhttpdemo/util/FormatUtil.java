package com.ericcode.rxhttpdemo.util;

import java.util.Calendar;
import java.util.Locale;

public class FormatUtil {

    public static String formatTime(long time) {
        Logger.i("time:" + time);
        Calendar oldTime = Calendar.getInstance(Locale.getDefault());
        oldTime.setTimeInMillis(time);
        Logger.i("old:" + oldTime.getTime());
        Calendar currentTime = Calendar.getInstance(Locale.getDefault());

        long timeCut = currentTime.getTimeInMillis() - oldTime.getTimeInMillis();

        int dayCut = (int) (timeCut / 1000f / 60f / 60f / 24f);
        if (dayCut < 1) {
            int hoursCut = (int) (timeCut / 1000f / 60f / 60f);
            if (hoursCut < 1) {
                int minutesCut = (int) (timeCut / 1000f / 60f);
                if (minutesCut < 1) {
                    return "刚刚";
                } else {
                    return minutesCut + "分钟前";
                }
            } else {
                return hoursCut + "小时前";
            }
        } else {
            return dayCut + "天前";
        }
    }

    private static String formatStr(String str) {
        return String.format(Locale.getDefault(), "%02d", str);
    }
}
