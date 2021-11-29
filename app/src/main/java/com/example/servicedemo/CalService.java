package com.example.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CalService extends Service {
    private static final String TAG="TAG";
    private LocalBinder mLocalBinder=new LocalBinder();

    public class LocalBinder extends Binder {
        CalService getService(){
            return CalService.this;
        }
    }

    public int add(int x,int y){
        return x+y;
    }

    public int sub(int x,int y){
        return x-y;
    }

    public int mul(int x,int y){
        return x*y;
    }

    public int div(int x,int y){
        if(y==0){

            System.out.println("除数不能为0");
            return 0;
        }else{
            return x/y;
        }
    }

    public CalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG,"Service onBind");
        return mLocalBinder;
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
    }
}