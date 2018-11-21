package com.aaa.sqlitemultithread.singleopenhelper.database.infodb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.aaa.sqlitemultithread.singleopenhelper.model.Student;
import com.aaa.sqlitemultithread.singleopenhelper.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class InfoDao {
    ReadWriteLock rwlT = new ReentrantReadWriteLock();//teacher表的锁
    ReadWriteLock rwlS = new ReentrantReadWriteLock();//student表的锁

    InfoDbManager infoDbManager;
    
    
    private static class Holder {
        private static InfoDao singleton = new InfoDao();
    }


    public static InfoDao getSingleton(){
        return Holder.singleton;
    }
    private InfoDao(){
        infoDbManager = InfoDbManager.getSingleton();
    }
    ArrayList<SQLiteDatabase> dbList = new ArrayList<>();
    public void testDbList(){
        for(SQLiteDatabase db : dbList){
            System.out.println(db.getClass().getName() + "@" + Integer.toHexString(db.hashCode()) + "====" + db.isOpen());
        }
    }
    
    public void deleteAll(){
        rwlT.writeLock().lock();// 取到写锁
        SQLiteDatabase db = null;
        try {
            db = infoDbManager.openDatabase();
//            db.beginTransaction();

            db.delete(Constant.TABLE_STUDENT, null, null);
            db.delete(Constant.TABLE_TEACHER, null, null);
//            db.endTransaction();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            if(infoDbManager != null)
                infoDbManager.closeDatabase();
            rwlT.writeLock().unlock();
        }
    }
    
    public void insertT(){
        rwlT.writeLock().lock();// 取到写锁
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = null;
        try {
            db = infoDbManager.openDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "teacher");
            contentValues.put("address", "address22");
            long insert = db.insert(Constant.TABLE_TEACHER, null, contentValues);

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            if(infoDbManager != null)
                infoDbManager.closeDatabase();
            rwlT.writeLock().unlock();
        }
               
    }
    public void insertS(){
        rwlS.writeLock().lock();
        SQLiteDatabase db = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            db = infoDbManager.openDatabase();
//            db.beginTransaction();

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "student");
            contentValues.put("age", 22);
            long insert = db.insert(Constant.TABLE_STUDENT, null, contentValues);

//            db.endTransaction();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            if(infoDbManager != null)
                infoDbManager.closeDatabase();
            rwlS.writeLock().unlock();
        }
        
    }
    String[] t_col = {Constant.ID, Constant.NAME, Constant.ADDRESS};
    public List<Teacher> queryT(){

        rwlT.readLock().lock();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            db = infoDbManager.openDatabase();
            dbList.add(db);
            // select * from Orders
            cursor = db.query(Constant.TABLE_TEACHER, t_col, null, null, null, null, null);
//            cursor = db.rawQuery("select * from teacher", null);

            return cur2teacher(cursor);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (cursor != null) 
                cursor.close();
            
            if(infoDbManager != null)
                infoDbManager.closeDatabase();
            rwlT.readLock().unlock();
        }

        return null;
        
        
        
    }
    String[] s_col = {Constant.ID, Constant.NAME, Constant.AGE};
    public List<Student> queryS(){

        rwlS.readLock().lock();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            db = infoDbManager.openDatabase();
            dbList.add(db);
            // select * from Orders
            cursor = db.query(Constant.TABLE_STUDENT, s_col, null, null, null, null, null);

            return cur2student(cursor);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (cursor != null)
                cursor.close();
            
            if(infoDbManager != null)
                infoDbManager.closeDatabase();
            rwlS.readLock().unlock();
        }

        return null;
        
        
        
    }

    private List<Teacher> cur2teacher(Cursor cursor) {
        if (cursor != null) {
            List<Teacher> orderList = new ArrayList<Teacher>(cursor.getCount());
            while (cursor.moveToNext()) {
                Teacher teacher = new Teacher();
                teacher.id = cursor.getString(cursor.getColumnIndex(Constant.ID));
                teacher.name = cursor.getString(cursor.getColumnIndex(Constant.NAME));
                teacher.address = cursor.getString(cursor.getColumnIndex(Constant.ADDRESS));
                orderList.add(teacher);
            }
            return orderList;
        }
        return null;
    }
    private List<Student> cur2student(Cursor cursor) {
        if (cursor != null) {
            List<Student> orderList = new ArrayList<Student>(cursor.getCount());
            while (cursor.moveToNext()) {
                Student teacher = new Student();
                teacher.id = cursor.getString(cursor.getColumnIndex(Constant.ID));
                teacher.name = cursor.getString(cursor.getColumnIndex(Constant.NAME));
                teacher.age = cursor.getInt(cursor.getColumnIndex(Constant.AGE));
                orderList.add(teacher);
            }
            return orderList;
        }
        return null;
    }
}
