package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG="myServicetag";
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.v(TAG,"onCreate()");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//其他组件(如活动)通过调用startService()来请求启动服务时，
        // 系统调用该方法。如果你实现该方法，你有责任在工作完成时通过stopSelf()或者stopService()方法来停止服务。
        Log.v(TAG,"onStartCommand()");
        int n=intent.getIntExtra("num",0);
        Log.v(TAG,"int is:"+n);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG,"onDestroy()");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {//当其他组件想要通过bindService()来绑定服务时，系统调用该方法。
        // 如果你实现该方法，你需要返回IBinder对象来提供一个接口，以便客户来与服务通信。你必须实现该方法，如果你不允许绑定，则直接返回null。
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}