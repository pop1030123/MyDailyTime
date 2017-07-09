package com.popfu.mydailytime.presenter;

import com.popfu.mydailytime.util.L;
import com.popfu.mydailytime.vo.TimeUnit;

/**
 * Created by pengfu on 09/07/2017.
 */

public class TimePresenter extends BasePresenter {

    public void addUnit(TimeUnit unit){
        int count = mTimeUnitDao.create(unit) ;
        L.d("添加Unit: count:"+count+unit);
    }


    public int updateUnit(TimeUnit unit){
        int count =  mTimeUnitDao.update(unit) ;
        L.d("修改Unit:"+unit);
        return count ;
    }
}
