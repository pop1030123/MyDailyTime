package com.popfu.mydailytime.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.popfu.mydailytime.util.L;
import com.popfu.mydailytime.vo.TimeUnit;

import java.sql.SQLException;

/**
 * Created by pengfu on 09/07/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "myDailyTime.db";

    private static final int DATABASE_VERSION = 1;

    private Dao<TimeUnit, Integer> noteDao = null;
    private RuntimeExceptionDao<TimeUnit, Integer> noteRuntimeDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            L.i(DatabaseHelper.class.getName()+" onCreate");
            TableUtils.createTable(connectionSource, TimeUnit.class);
        } catch (SQLException e) {
            L.e(DatabaseHelper.class.getName() + " Can't create database", e);
            throw new RuntimeException(e);
        }

        // here we try inserting data in the on-create as a test
        RuntimeExceptionDao<TimeUnit, Integer> dao = getTimeUnitDao();
        long millis = System.currentTimeMillis();
        // create some entries in the onCreate
        TimeUnit simple = new TimeUnit("吃饭");
        simple.setDuration(1000);
        dao.create(simple);
        simple = new TimeUnit("工作");
        simple.setDuration(2000);
        dao.create(simple);
        L.i(DatabaseHelper.class.getName()+" created new entries in onCreate: " + millis);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public Dao<TimeUnit, Integer> getDao() throws SQLException {
        if (noteDao == null) {
            noteDao = getDao(TimeUnit.class);
        }
        return noteDao;
    }

    public RuntimeExceptionDao<TimeUnit, Integer> getTimeUnitDao() {
        if (noteRuntimeDao == null) {
            noteRuntimeDao = getRuntimeExceptionDao(TimeUnit.class);
        }
        return noteRuntimeDao;
    }


    @Override
    public void close() {
        super.close();
        noteDao = null;
        noteRuntimeDao = null;
    }
}
