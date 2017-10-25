package com.example.administrator.myservicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * @author libohan
 *  邮箱:76681287@qq.com
 *  create on 2017/10/25.
 */

public class StudentService extends Service {
    String[] name=new String[]{"lee","sun","hao"};
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new StudentBinder();
    }
    private class StudentBinder extends Binder implements Student
    {

        @Override
        public String getStudentName(int num) {
            return getname(num);
        }
    }
    private String getname(int num)
    {
        if (num>0&num<4)
        {
            return name[num];
        }
        else {
            return null;
        }
    }
}
