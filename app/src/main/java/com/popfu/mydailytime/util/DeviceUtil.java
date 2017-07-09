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

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = MDTApp.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = MDTApp.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
