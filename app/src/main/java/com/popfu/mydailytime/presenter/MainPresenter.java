package com.popfu.mydailytime.presenter;

import com.popfu.mydailytime.util.L;
import com.popfu.mydailytime.vo.TimeUnit;

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





}
