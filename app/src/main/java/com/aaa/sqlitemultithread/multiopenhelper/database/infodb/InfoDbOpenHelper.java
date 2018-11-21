package com.aaa.sqlitemultithread.multiopenhelper.database.infodb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class InfoDbOpenHelper extends SQLiteOpenHelper {
    public InfoDbOpenHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
        if(Build.VERSION.SDK_INT >= 11){
            getWritableDatabase().enableWriteAheadLogging();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table teacher(id integer primary key autoincrement,name varchar(64),address varchar(64))";
        db.execSQL(sql);
        sql = "create table student(id integer primary key autoincrement,name varchar(64),age integer)";
        db.execSQL(sql);
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
