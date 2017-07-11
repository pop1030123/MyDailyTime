package com.popfu.mydailytime.util;

import com.popfu.mydailytime.MDTApp;
import com.popfu.mydailytime.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by pengfu on 10/07/2017.
 */

public final class TimeUtil {


    public static final String FORMAT_yyyyMMdd = "yyyy/MM/dd" ;

    public static String getDuration(long durationInSecond){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        return formatter.format(durationInSecond*1000);
    }


    public static String getDateString(long timeInMillis){
        if(isToday(timeInMillis)){
            return MDTApp.getAppContext().getString(R.string.today) ;
        } else if(isYesterday(timeInMillis)){
            return MDTApp.getAppContext().getString(R.string.yesterday) ;
        }else{
            SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_yyyyMMdd);
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            return formatter.format(timeInMillis);
        }
    }


    public static boolean isToday(long timeInMillis){
        Calendar calendar = Calendar.getInstance() ;
        int today = calendar.get(Calendar.DAY_OF_YEAR) ;
        calendar.setTimeInMillis(timeInMillis);
        int otherDay = calendar.get(Calendar.DAY_OF_YEAR) ;
        return today == otherDay ;
    }

    public static boolean isYesterday(long timeInMillis){
        Calendar calendar = Calendar.getInstance() ;
        calendar.add(Calendar.DATE ,-1);
        int yesterday = calendar.get(Calendar.DAY_OF_YEAR) ;
        calendar.setTimeInMillis(timeInMillis);
        int otherDay = calendar.get(Calendar.DAY_OF_YEAR) ;
        return yesterday == otherDay ;
    }

}
