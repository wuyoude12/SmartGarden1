package com.homidea.smartgarden.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.homidea.smartgarden.adapter.HistoryDataAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HistoryDataActivity extends AppCompatActivity {
    private static final String TAG = "HistoryDataActivity";
    HistoryDataAdapter adapter;
    ListView historyList;
    String sensorType;
    List<HashMap<String,Object>> dataMap;    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historydata);

        Intent in = getIntent();
        sensorType = in.getStringExtra("type");
        List<HashMap<String, Object>> dataMap = (List<HashMap<String, Object>>)in.getSerializableExtra("historyData");

        historyList = (ListView)findViewById(R.id.historyData);
        adapter = new HistoryDataAdapter(this,dataMap,sensorType);
        Log.i(TAG, "onCreate: "+dataMap);

        historyList.setAdapter(adapter);



    }
}
