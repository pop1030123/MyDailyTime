package com.popfu.mydailytime.event;

import com.popfu.mydailytime.vo.TimeUnit;

/**
 * Created by pengfu on 09/07/2017.
 */

public class EventUnit {

    private TimeUnit unit ;

    public EventUnit(TimeUnit unit){
        this.unit = unit ;
    }

    public TimeUnit getUnit() {
        return unit;
    }
}
