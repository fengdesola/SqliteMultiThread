package com.aaa.sqlitemultithread.singleopenhelper.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class DbManager {
    private AtomicInteger mOpenCounter = new AtomicInteger();
    private SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public DbManager(SQLiteOpenHelper helper) {
        mDatabaseHelper = helper;
    }


    //打开数据库方法
    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            // Opening new database
            try {
                mDatabase = mDatabaseHelper.getWritableDatabase();
            } catch (Exception e) {
                mDatabase = mDatabaseHelper.getReadableDatabase();
            }
        }
        return mDatabase;
    }

    //关闭数据库方法
    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            // Closing database
            if (mDatabase != null)
                mDatabase.close();
        }
    }
}
