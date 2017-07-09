package com.popfu.mydailytime;

import android.app.Application;
import android.content.Context;

import com.popfu.mydailytime.util.toast.ToastUtil;

/**
 * Created by pengfu on 09/07/2017.
 */

public class MDTApp extends Application {


    private static Context instance ;



    public static Context getAppContext(){
        return instance ;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this ;
        ToastUtil.initialize(this);
    }
}
