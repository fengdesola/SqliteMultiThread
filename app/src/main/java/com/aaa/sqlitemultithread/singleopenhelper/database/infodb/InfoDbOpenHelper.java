package com.aaa.sqlitemultithread.singleopenhelper.database.infodb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InfoDbOpenHelper extends SQLiteOpenHelper {
    public InfoDbOpenHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
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
