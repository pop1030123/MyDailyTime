package com.popfu.mydailytime.presenter;

import com.popfu.mydailytime.util.L;
import com.popfu.mydailytime.vo.TimeUnit;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pengfu on 08/07/2017.
 */

public class MainPresenter extends BasePresenter{


    public List<TimeUnit> getAllTimeUnits() {
        List<TimeUnit> timeUnits = mTimeUnitDao.queryForAll();
        L.d("所有的timeUnits:" + timeUnits);
        return timeUnits;
    }

    public long getMaxMillis(){
        try {
            TimeUnit timeUnit = mTimeUnitDao.queryBuilder().orderBy("duration" ,false).queryForFirst() ;
            L.d("getMaxMillis:"+timeUnit);
            return timeUnit.getDuration() ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0 ;
    }

}
