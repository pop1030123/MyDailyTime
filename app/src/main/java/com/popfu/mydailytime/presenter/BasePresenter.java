package com.popfu.mydailytime.presenter;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.popfu.mydailytime.MDTApp;
import com.popfu.mydailytime.db.DatabaseHelper;
import com.popfu.mydailytime.vo.TimeUnit;

/**
 * Created by pengfu on 24/06/2017.
 */

public class BasePresenter {


    protected DatabaseHelper databaseHelper = null;


    protected RuntimeExceptionDao<TimeUnit, Integer> mTimeUnitDao;


    public BasePresenter(){
        databaseHelper = getHelper() ;
        mTimeUnitDao = databaseHelper.getTimeUnitDao() ;

    }

    /**
     * You'll need this in your class to get the helper from the manager once per class.
     */
    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(MDTApp.getAppContext(), DatabaseHelper.class);
        }
        return databaseHelper;
    }
}
