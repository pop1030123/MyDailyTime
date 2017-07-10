package com.carlosdelachica.timelydigitalclock.model.data.clock;

import android.util.Log;

import com.carlosdelachica.timelydigitalclock.model.data.base.TimeSet;
import com.carlosdelachica.timelydigitalclock.model.data.base.TimeUnitSet;
import com.carlosdelachica.timelydigitalclock.model.data.base.TimeUnitType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {

    public static final String TAG = "Clock" ;

    private Timer timer = new Timer();

    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

    private ClockMode clockMode;
    private ClockCallback callback;

    private long mStartMillis;

    public Clock(ClockCallback callback) {
        this(ClockMode.FORMAT_24, callback ,0);
    }

    public Clock(ClockMode clockMode, ClockCallback callback ,long millis) {
        this.clockMode = clockMode;
        this.callback = callback;
        this.mStartMillis = millis ;
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        TimeUnitSet[] sets = getTimeUnitSets(millis) ;
        callback.onTimeUpdated(new TimeSet(sets[0], sets[1], sets[2]));
    }

    public void startTime() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mStartMillis += 1000 ;
                TimeUnitSet[] sets = getTimeUnitSets(mStartMillis) ;
                callback.onTimeUpdated(new TimeSet(sets[0], sets[1], sets[2]));

            }
        }, 0, 1000);
    }

    public void stopTime(){
        timer.cancel();
    }

    public long getMillis(){
        return mStartMillis ;
    }

    private TimeUnitSet[] getTimeUnitSets(long millis){
        TimeUnitSet[] sets = new TimeUnitSet[3] ;

        String hms = formatter.format(millis);
        Calendar calendar = formatter.getCalendar() ;

        int hour = calendar.get(clockMode == ClockMode.FORMAT_24 ? Calendar.HOUR_OF_DAY : Calendar.HOUR);
        int hourUnit = hour % 10;
        int hourTens = hour / 10;
        int minute = calendar.get(Calendar.MINUTE);
        int minuteUnit = minute % 10;
        int minuteTens = minute / 10;
        int second = calendar.get(Calendar.SECOND);
        int secondUnit = second % 10;
        int secondTens = second / 10;

        TimeUnitSet seconds, minutes, hours;

        seconds = new TimeUnitSet(TimeUnitType.SECONDS);
        minutes = new TimeUnitSet(TimeUnitType.MINUTES);
        hours = new TimeUnitSet(TimeUnitType.HOURS);

        seconds.updateValues(secondUnit, secondTens);
        minutes.updateValues(minuteUnit, minuteTens);
        hours.updateValues(hourUnit, hourTens);

        sets[0] = seconds ;
        sets[1] = minutes ;
        sets[2] = hours ;
        return sets ;
    }

    public interface ClockCallback {
        public void onTimeUpdated(TimeSet timeSet);
    }

    public enum ClockMode {
        FORMAT_24,
        FORMAT_12
    }
}
