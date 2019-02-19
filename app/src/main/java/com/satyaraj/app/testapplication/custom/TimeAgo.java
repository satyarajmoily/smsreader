package com.satyaraj.app.testapplication.custom;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TimeAgo {

    private static String HOURS_0 = "0 hour ago";
    private static String HOURS_1 = "1 hour ago";
    private static String HOURS_2 = "2 hours ago";
    private static String HOURS_3 = "3 hours ago";
    private static String HOURS_6 = "6 hours ago";
    private static String HOURS_12 = "12 hours ago";
    public static String HOURS_DAY = "1 day ago";


    public static String covertTimeToText(long timeInMillis) {

        Calendar calendar = Calendar.getInstance();

        long dateDiff = calendar.getTimeInMillis() - timeInMillis;

        long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);

        if (hour < 1) {
            return HOURS_0;
        }else if (hour < 2){
            return HOURS_1;
        }else if (hour < 3){
            return HOURS_2;
        }else if (hour < 6 ){
            return HOURS_3;
        }else if (hour < 12){
            return HOURS_6;
        }else if (hour < 24){
            return  HOURS_12;
        }
        return null;
    }
}