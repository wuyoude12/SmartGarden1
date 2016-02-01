package com.homidea.smartgarden.helper;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wuyoude on 2016/1/27.
 */
public class GetRequest {

    private static final String TAG = "GetRequest";
    String result = null;
    Gson gson = new Gson();
    OkHttpClient okHttpClient = new OkHttpClient();
    private List<HashMap<Object, Object>> gatewayList = null;   //获取设备的信息列表
    private List<List<HashMap<Object, Object>>> childList = null;   //获取设备下传感器信息列表
    private List<HashMap<String,Object>> historyDataList = null;    //历史数据列表

    public GetRequest() {

    }



    public List<HashMap<Object, Object>> getGatewayList() {
        return gatewayList;
    }

    public void setGatewayList(List<HashMap<Object, Object>> gatewayList) {
        this.gatewayList = gatewayList;
    }

    public List<List<HashMap<Object, Object>>> getChildList() {
        return childList;
    }

    public void setChildList(List<List<HashMap<Object, Object>>> childList) {
        this.childList = childList;
    }

    public List<HashMap<String, Object>> getHistoryDataList() {
        return historyDataList;
    }

    public void setHistoryDataList(List<HashMap<String, Object>> historyDataList) {
        this.historyDataList = historyDataList;
    }

    /**
     * 获取用户所有设备及传感器GET请求
     */
    public void getAllSensors() {

        Request request = new Request.Builder()
                .url("http://www.lewei50.com/api/V1/user/getSensorsWithGateway?sensorType=")
                .get()
                .addHeader("userkey", "99305fc98b834a41881d45362876aa49")
                .build();

        try {
            Response response = null;
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
                Log.i(TAG, "getAllSensors: " + result);
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AllSensorsGson() {
        gatewayList = new ArrayList<HashMap<Object, Object>>();
        childList = new ArrayList<List<HashMap<Object, Object>>>();
        getAllSensors();

        List<AllSensorsBean> allSensors = new ArrayList<>();
        allSensors = gson.fromJson(result, new TypeToken<List<AllSensorsBean>>() {
        }.getType());
        for (int i = 0; i < allSensors.size(); i++) {
            HashMap<Object, Object> gatewayMap = new HashMap<>();
            gatewayMap.put("id", allSensors.get(i).getId());
            gatewayMap.put("idName", allSensors.get(i).getIdName());
            gatewayMap.put("templateName", allSensors.get(i).getTemplateName());
            gatewayMap.put("name", allSensors.get(i).getName());
            gatewayMap.put("typeName", allSensors.get(i).getTypeName());
            gatewayMap.put("description", allSensors.get(i).getDescription());
            gatewayMap.put("isPublic", allSensors.get(i).isPublic());
            gatewayMap.put("isControlled", allSensors.get(i).isControlled());
            gatewayMap.put("internetAvailable", allSensors.get(i).isInternetAvailable());
            gatewayMap.put("apiAddress", allSensors.get(i).getApiAddress());
            gatewayMap.put("apiAddressInternet", allSensors.get(i).getApiAddressInternet());
            List<AllSensorsBean.Sensors> sensors = allSensors.get(i).getSensors();
            List<HashMap<Object, Object>> sensorsList = new ArrayList<HashMap<Object, Object>>();
            for (int j = 0; j < sensors.size(); j++) {
                HashMap<Object, Object> sensorsMap = new HashMap<>();
                sensorsMap.put("id", sensors.get(j).getId());
                sensorsMap.put("idName", sensors.get(j).getIdName());
                sensorsMap.put("name", sensors.get(j).getName());
                sensorsMap.put("value", sensors.get(j).getValue());
                sensorsMap.put("type", sensors.get(j).getType());
                sensorsMap.put("typeName", sensors.get(j).getTypeName());
                sensorsMap.put("unit", sensors.get(j).getUnit());
                sensorsMap.put("description", sensors.get(j).getDescription());
                sensorsMap.put("lastUpdateTime", sensors.get(j).getLastUpdateTime());
                sensorsMap.put("isOnline", sensors.get(j).isOnline());
                sensorsMap.put("isError", sensors.get(j).isError());
                sensorsMap.put("isAlarm", sensors.get(j).isAlarm());
                sensorsList.add(sensorsMap);
            }
            gatewayMap.put("sensors", sensorsList);

            gatewayList.add(gatewayMap);

            childList.add(sensorsList);
            Log.i(TAG, "childItem: " + childList.get(i));

        }

    }

    public void getHistoryData(String sensorId){
        Request request = new Request.Builder()
                .url("http://www.lewei50.com/api/V1/sensor/GetHistoryData/"+sensorId)
                .get()
                .addHeader("userkey","99305fc98b834a41881d45362876aa49")
                .build();

        try {
            Response response = null;
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                result = response.body().string();
            }else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void HistoryDataGson(String sensorId){
        historyDataList = new ArrayList<HashMap<String,Object>>();
        getHistoryData(sensorId);
        HistoryDataBean historyDataBean = gson.fromJson(result, HistoryDataBean.class);
        boolean hisIsSuccessfui = historyDataBean.isSuccessful();
        String hisMessage = historyDataBean.getMessage();
        List<HistoryDataBean.HistoryData> datas = historyDataBean.getData();
        for (int i = 0;i<datas.size();i++){
            HashMap<String,Object> dataMap = new HashMap<>();
            dataMap.put("updataTime",datas.get(i).getUpdateTime());
            dataMap.put("value",datas.get(i).getValue());
            historyDataList.add(dataMap);
        }
        Log.i(TAG, "HistoryDataGson: "+historyDataList);
    }

}
