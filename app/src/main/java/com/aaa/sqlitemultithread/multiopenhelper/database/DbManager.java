package com.aaa.sqlitemultithread.multiopenhelper.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DbManager {
    private SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public DbManager(SQLiteOpenHelper helper) {
        mDatabaseHelper = helper;
    }


    //打开数据库方法
    public SQLiteDatabase openDatabase() {
        // Opening new database
        try {
            mDatabase = mDatabaseHelper.getWritableDatabase();
        } catch (Exception e) {
            mDatabase = mDatabaseHelper.getReadableDatabase();
        }
        return mDatabase;
    }

    //关闭数据库方法
    public void closeDatabase() {
        if (mDatabase != null)
            mDatabase.close();
    }
}
