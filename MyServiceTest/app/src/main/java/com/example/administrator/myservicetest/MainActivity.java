package com.example.administrator.myservicetest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
       new Thread()
       {
           @Override
           public void run() {
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               Looper.prepare();
               String name=student.getStudentName(1);
               Log.e("LBH",name);
               Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
               Looper.loop();
           }
       }.start();
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
