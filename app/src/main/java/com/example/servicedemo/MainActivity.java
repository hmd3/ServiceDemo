package com.example.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="TAG";

    ServiceConnection mServiceConnection;
    CalService mCalcService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"Service onCreate");
        mServiceConnection =new ServiceConnection() {
            @Override
            //当Service被创建后，调用方和Service就通过IBinder建立了联系，此时该方法被回调
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.v(TAG,"onServiceConnected");
                mCalcService=((CalService.LocalBinder) iBinder).getService();
            }

            @Override
            //当调用方和Service中断联系（Service可能是正常终止，也可能由于其它原因被异常终止）后，回调此方法

            public void onServiceDisconnected(ComponentName componentName) {
                Log.v(TAG,"onServiceDisconnected");
            }
        };



        Button btnStart=(Button)findViewById(R.id.btnStart);
        Button btnEnd=(Button)findViewById(R.id.btnEnd);

        Button btnAdd=(Button)findViewById(R.id.btnadd);
        Button btnSub=(Button)findViewById(R.id.btnsub);
        Button btnMul=(Button)findViewById(R.id.btnmul);
        Button btnDiv=(Button)findViewById(R.id.btndiv);


        btnStart.setOnClickListener(new View.OnClickListener() {//启动服务
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this,MyService.class);
//                intent.putExtra("num",10);

                Intent intent=new Intent(MainActivity.this,CalService.class);
                bindService(intent,mServiceConnection, Service.BIND_AUTO_CREATE);

                startService(intent);
            }
        });
//
        btnEnd.setOnClickListener(new View.OnClickListener() {//终止服务
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(MainActivity.this,MyService.class);
                Intent intent=new Intent(MainActivity.this,CalService.class);
                if(mCalcService!=null){
                    unbindService(mServiceConnection);
                }
                Log.v(TAG,"Stop");
                stopService(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG,"Service add");
                if(mCalcService!=null){
                    Toast.makeText(MainActivity.this,"Using Service add:"+mCalcService.add(10,5),Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG,"Service sub");
                if(mCalcService!=null){
                    Toast.makeText(MainActivity.this,"Using Service sub:"+mCalcService.sub(10,5),Toast.LENGTH_LONG).show();
                }
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG,"Service mul");
                if(mCalcService!=null){
                    Toast.makeText(MainActivity.this,"Using Service mul:"+mCalcService.mul(10,5),Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG,"Service div");
                if(mCalcService!=null){
                    Toast.makeText(MainActivity.this,"Using Service div:"+mCalcService.div(10,5),Toast.LENGTH_LONG).show();
                }
            }
        });

//        public void onButtonStartServiceClick(View view){
//            Intent intent=new Intent(MainActivity.this,CalService.class);
//            bindService(intent,mServiceConnection, Service.BIND_AUTO_CREATE);
//        }

//        public void onButtonStopServiceClick(View view){
//            if(mCalcService!=null){
//                unbindService(mServiceConnection);
//            }
//        }

//        public void onButtonAddClick(View view){
//            if(mCalcService!=null){
//                Toast.makeText(MainActivity.this,"Using Service add:"+mCalcService.add(10,5),Toast.LENGTH_LONG).show();
//            }
//        }



    }
}