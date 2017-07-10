package com.popfu.mydailytime.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by pengfu on 10/07/2017.
 */

public final class TimeUtil {


    public static String getDuration(long durationInSecond){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return formatter.format(durationInSecond*1000);
    }
}
