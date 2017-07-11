package com.popfu.mydailytime.presenter;

import com.popfu.mydailytime.util.L;
import com.popfu.mydailytime.vo.TimeUnit;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import static com.popfu.mydailytime.util.TimeUtil.FORMAT_yyyyMMdd;

/**
 * Created by pengfu on 08/07/2017.
 */

public class MainPresenter extends BasePresenter{


    public List<TimeUnit> getAllTimeUnits() {
        List<TimeUnit> timeUnits = null;
        try {
            timeUnits = mTimeUnitDao.queryBuilder().orderBy("startTime" ,false).query();

            SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_yyyyMMdd);
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            String lastDateStr = null ;
            for(TimeUnit unit : timeUnits){
                String unitDate = formatter.format(unit.getStartTime()) ;
                L.d("unitDate:"+unitDate);
                if(!unitDate.equals(lastDateStr)){
                    unit.setShowDateTime(true);
                    lastDateStr = unitDate ;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
