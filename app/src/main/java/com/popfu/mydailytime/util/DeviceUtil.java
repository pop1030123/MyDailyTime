package com.popfu.mydailytime.util;

import android.util.DisplayMetrics;

import com.popfu.mydailytime.MDTApp;

/**
 * Created by pengfu on 09/07/2017.
 */

public final class DeviceUtil {


    public static int getScreenWidth(){
        DisplayMetrics dm = MDTApp.getAppContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        DisplayMetrics dm = MDTApp.getAppContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
