package com.aaa.sqlitemultithread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aaa.sqlitemultithread.multiopenhelper.MultiActivity;
import com.aaa.sqlitemultithread.singleopenhelper.SingleActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.context = getApplicationContext();
        
        findViewById(R.id.btnMulti).setOnClickListener(this);
        findViewById(R.id.btnSingle).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMulti:
                startActivity(new Intent(MainActivity.this, MultiActivity.class));
                break;
            case R.id.btnSingle:
                startActivity(new Intent(MainActivity.this, SingleActivity.class));
                break;
        }
    }
}
