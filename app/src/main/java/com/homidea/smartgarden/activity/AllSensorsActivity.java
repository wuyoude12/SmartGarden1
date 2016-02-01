package com.homidea.smartgarden.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.homidea.smartgarden.adapter.ExpandListViewAdapter;
import com.homidea.smartgarden.helper.GetRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllSensorsActivity extends AppCompatActivity {

    private static final String TAG = "AllSensorsActivity";
    private ExpandableListView expandableListView;
    ExpandListViewAdapter expandListViewAdapter;
    GetRequest getRequest = new GetRequest();
    String sensorsId;
    String sensorType;
    List<HashMap<String,Object>> historyData = new ArrayList<HashMap<String,Object>>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_sensors);
        expandableListView = (ExpandableListView)findViewById(R.id.expandList);
        final Intent intent = getIntent();
        List<HashMap<Object, Object>> groupList = (List<HashMap<Object, Object>>)intent.getSerializableExtra("group");
        final List<List<HashMap<Object, Object>>> childList = (List<List<HashMap<Object, Object>>>)intent.getSerializableExtra("child");

        expandListViewAdapter = new ExpandListViewAdapter(this,groupList,childList);
        expandableListView.setAdapter(expandListViewAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                sensorsId = childList.get(groupPosition).get(childPosition).get("id").toString();
                sensorType = childList.get(groupPosition).get(childPosition).get("type").toString();
                new Thread( new Runnable() {
                    @Override
                    public void run() {
                        getRequest.HistoryDataGson(sensorsId);
                        historyData = getRequest.getHistoryDataList();
                        Log.i(TAG, "onChildClick: "+historyData);
                    }
                }).start();
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent in = new Intent(AllSensorsActivity.this,HistoryDataActivity.class);
                in.putExtra("type",sensorType);
                in.putExtra("historyData", (Serializable) historyData);
                Log.i(TAG, "onChildClick: " + historyData);
                startActivity(in);
                return false;
            }
        });
    }


}
