package com.example.administrator.myservicetest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class AidlActivity extends AppCompatActivity {

    private MyStudentInterface myStudentInterface;
    private MyAidlServiceConn conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent service=new Intent("com.example.aidlStudent");
        conn=new MyAidlServiceConn();
        bindService(service,conn, Context.BIND_AUTO_CREATE);
        new Thread()
        {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    String name=myStudentInterface.getStudentName(2);
                    Log.e("LBH",name);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
    class MyAidlServiceConn implements ServiceConnection
    {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            myStudentInterface=MyStudentInterface.Stub.asInterface(iBinder);
            Log.e("LBH"," myStudentInterface="+ myStudentInterface);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
               myStudentInterface=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
