package com.aaa.sqlitemultithread.multiopenhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aaa.sqlitemultithread.R;
import com.aaa.sqlitemultithread.multiopenhelper.database.infodb.InfoDao;
import com.aaa.sqlitemultithread.multiopenhelper.model.Student;
import com.aaa.sqlitemultithread.multiopenhelper.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class MultiActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi);
        
        findViewById(R.id.btnInsertTeacher).setOnClickListener(this);
        findViewById(R.id.btnInsertStudent).setOnClickListener(this);
        findViewById(R.id.btnQueryTeacher).setOnClickListener(this);
        findViewById(R.id.btnQueryStudent).setOnClickListener(this);
        findViewById(R.id.btnInsertBoth).setOnClickListener(this);
        findViewById(R.id.btnQueryBoth).setOnClickListener(this);
        findViewById(R.id.btnInsertQueryBoth).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnInsertTeacher:
                insertTeacher();
                break;
            case R.id.btnInsertStudent:
                insertStudent();
                break;
            case R.id.btnQueryTeacher:
                queryTeacher();
                break;
            case R.id.btnQueryStudent:
                queryStudent();
                break;
            case R.id.btnInsertBoth:
                insertTeacher();
                insertStudent();
                break;
            case R.id.btnQueryBoth:
                queryStudent();
                queryTeacher();
                break;
            case R.id.btnInsertQueryBoth:
                insertTeacher();
                queryTeacher();
                break;
            
        }
    }


    private void queryTeacher() {
        for(int i = 0 ; i< 3; i++)
            new Thread(){
                @Override
                public void run() {
                    InfoDao dao = InfoDao.getSingleton();
                    List<Teacher> teachers = dao.queryT();
                    System.out.println("t=="+teachers);
                }
            }.start();
    }
    private void queryStudent() {
        for(int i = 0 ; i< 3; i++)
            new Thread(){
                @Override
                public void run() {
                    InfoDao dao = InfoDao.getSingleton();
                    List<Student> students = dao.queryS();
                    System.out.println("s=="+students);
                }
            }.start();
    }

    public void insertStudent(){
        for(int i =0 ; i < 3; i++){
            new Thread(){
                @Override
                public void run() {
                    InfoDao dao = InfoDao.getSingleton();
                    dao.insertS(getStudentList());
                    System.out.println(Thread.currentThread().getId() + "====S");
                }
            }.start();
        }
    }

    private void insertTeacher() {

        for(int i =0 ; i < 3; i++){
            new Thread(){
                @Override
                public void run() {
                    InfoDao dao = InfoDao.getSingleton();
                    dao.insertT();
                    System.out.println(Thread.currentThread().getId() + "====T");
                }
            }.start();
        }
    }


    private ArrayList<Student> getStudentList() {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student( "s11", 11));
        list.add(new Student( "s22", 22));
        return list;
    }
}
