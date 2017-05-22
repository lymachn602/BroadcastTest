package com.example.linyuming.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//本地广播无法通过静态方法进行注册
public class MainActivity extends AppCompatActivity {
      private IntentFilter intentFilter;
      //private NetworkChangeReceiver networkChangeReceiver;
      private Button btn;
      private LocalReceiver localReceiver;
      private LocalBroadcastManager localBroadcastManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.MY_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
       /*networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);*/
        btn = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent("com.example.broadcasttest.MY_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);
                //sendOrderedBroadcast(intent,null);//有序广播的发送方法
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       localBroadcastManager. unregisterReceiver(localReceiver);
    }
   /* class NetworkChangeReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo network = connectivityManager.getActiveNetworkInfo();
            if(network!=null&&network.isAvailable()){
                Toast.makeText(context,"network is Available",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"network is unAvailable",Toast.LENGTH_LONG).show();
            }

        }
    }*/
    class LocalReceiver extends BroadcastReceiver {

       @Override
       public void onReceive(Context context, Intent intent) {
           Toast.makeText(context,"received local broadcast",Toast.LENGTH_SHORT).show();
       }
   }
}
