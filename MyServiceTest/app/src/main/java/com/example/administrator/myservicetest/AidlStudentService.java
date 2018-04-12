package com.example.administrator.myservicetest;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/10/25.
 */
public class AidlStudentService extends Service{
    String[] name=new String[]{"mao","jiang","deng"};
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new AidlBinder();
    }
    class AidlBinder extends MyStudentInterface.Stub
    {

        @Override
        public String getStudentName(int num) throws RemoteException {

            return getStudentNames(num);
        }
    }
    private String getStudentNames(int num)
    {
        if (num>0&&num<4)
        {
            return name[num];
        }else
        {
            return null;
        }

    }
}
