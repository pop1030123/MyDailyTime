package com.popfu.mydailytime.vo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by pengfu on 08/07/2017.
 */

@DatabaseTable(tableName = "time_unit")
public class TimeUnit implements Serializable {


    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String name ;
    @DatabaseField
    private long startTime ;

    /**
     * 时长，以秒为单位
     */
    @DatabaseField
    private long duration;

    public TimeUnit(){}

    public TimeUnit(String name){
        this.name = name ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getDurationString(){
        // TODO: 08/07/2017
        return String.valueOf(duration) ;
    }


    @Override
    public String toString() {
        return "TimeUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", duration=" + duration +
                '}';
    }
}
