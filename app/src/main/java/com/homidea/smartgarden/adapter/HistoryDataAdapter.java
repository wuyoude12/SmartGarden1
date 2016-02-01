package com.homidea.smartgarden.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.homidea.smartgarden.activity.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wuyoude on 2016/2/1.
 */
public class HistoryDataAdapter extends BaseAdapter {
    Context context;
    List<HashMap<String, Object>> dataMap;
    String sensorType;

    public HistoryDataAdapter(Context context, List<HashMap<String, Object>> dataMap, String sensorType) {
        this.context = context;
        this.dataMap = dataMap;
        this.sensorType = sensorType;
    }

    @Override
    public int getCount() {
        return dataMap.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.acticity_historyitem,null);
            viewHolder.updataTime = (TextView) convertView.findViewById(R.id.updataTimeText);
            viewHolder.value = (TextView) convertView.findViewById(R.id.data);
            viewHolder.sign = (TextView) convertView.findViewById(R.id.sign);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.updataTime.setText(dataMap.get(position).get("updataTime").toString());
        viewHolder.value.setText(dataMap.get(position).get("value").toString());
        switch (sensorType) {
            case "TEMP":
                viewHolder.sign.setText("℃");
                break;
            case "PRES":
                viewHolder.sign.setText("hpa");
                break;
            case "HUMI":
                viewHolder.sign.setText("%");
                break;
            case "GPSS":
                viewHolder.sign.setText("");
                break;
            case "DIST":
                viewHolder.sign.setText("M");
                break;
            case "JDQI":
                viewHolder.sign.setText("");
                break;
            case "CO22":
                viewHolder.sign.setText("PPM");
                break;
            case "KLUX":
                viewHolder.sign.setText("LUX");
                break;
            case "PMUG":
                viewHolder.sign.setText("ug/m3");
                break;
            case "PMU2":
                viewHolder.sign.setText("ug/m3");
                break;
            case "PMU3":
                viewHolder.sign.setText("ug/m3");
                break;
            case "OTHR":
                viewHolder.sign.setText("");
                break;
            default:
                break;
        }

        return convertView;
    }

    class ViewHolder {
        TextView updataTime;
        TextView value;
        TextView sign;
    }
}
