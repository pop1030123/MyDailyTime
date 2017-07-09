package com.popfu.mydailytime.util;

import android.util.Log;

import java.sql.SQLException;

/**
 * Created by pengfu on 24/06/2017.
 */

public final class L {

    private static final String TAG ="MyDailyTime" ;

    public static void d(String message){
        Log.d(TAG ,message) ;
    }


    public static void i(String message){
        Log.i(TAG ,message) ;
    }

    public static void e(String message, SQLException e) {
        Log.e(TAG ,message ,e) ;
    }
}
