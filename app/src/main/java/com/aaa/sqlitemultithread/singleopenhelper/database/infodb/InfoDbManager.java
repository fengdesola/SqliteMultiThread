package com.aaa.sqlitemultithread.singleopenhelper.database.infodb;

import android.database.sqlite.SQLiteOpenHelper;

import com.aaa.sqlitemultithread.App;
import com.aaa.sqlitemultithread.singleopenhelper.database.DbManager;


public class InfoDbManager extends DbManager {

    private static class Holder {
        private static InfoDbManager singleton = new InfoDbManager(new InfoDbOpenHelper(App.context));
    }


    public static InfoDbManager getSingleton() {
        return Holder.singleton;
    }

    public InfoDbManager(SQLiteOpenHelper helper) {
        super(helper);
    }
}
