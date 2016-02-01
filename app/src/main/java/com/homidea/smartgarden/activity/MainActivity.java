package com.homidea.smartgarden.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.homidea.smartgarden.helper.GetRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    private ImageButton equipment;
    int count = 0;
    Thread gateway = null;
    Handler mHandler;
    private List<HashMap<Object, Object>> groupList;
    private List<List<HashMap<Object, Object>>> childList;

    GetRequest getRequest = new GetRequest();

    Runnable run = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        groupList = new ArrayList<HashMap<Object, Object>>();
        childList = new ArrayList<List<HashMap<Object, Object>>>();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };

        equipment = (ImageButton)findViewById(R.id.btn_equipment);
        equipment.setOnClickListener(this);

    }

//    private void ThreadStart(){
//        gateway = new Thread(run);
//        gateway.start();
//
//        count++;
//        Log.i(TAG, "ThreadStart: " + count);
//    }
//    private void ThreadStop(){
//        gateway = null;
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_equipment:


                Intent intent = new Intent(MainActivity.this,AllSensorsActivity.class);
                intent.putExtra("group", (Serializable) groupList);
                intent.putExtra("child", (Serializable) childList);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        run = new Runnable() {
            @Override
            public void run() {
                getRequest.AllSensorsGson();

                groupList = getRequest.getGatewayList();
                childList = getRequest.getChildList();


                Message message = new Message();
                mHandler.sendMessage(message);
            }
        };
        Log.i(TAG, "onStart: ");
        if (gateway == null){
            gateway = new Thread(run);
            gateway.start();
        }else {
            gateway = null;
            gateway = new Thread(run);
            gateway.start();
        }
        run = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
        run = null;
        gateway = null;

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
