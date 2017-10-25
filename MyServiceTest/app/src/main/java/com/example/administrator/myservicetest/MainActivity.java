package com.example.administrator.myservicetest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private Student student;
    private MyStudentServiceConn conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent(this,StudentService.class);
        conn=new MyStudentServiceConn();
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
       String name=student.getStudentName(1);
        Log.e("LBH",name);
    }
    class MyStudentServiceConn implements ServiceConnection
    {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            student=(Student) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
