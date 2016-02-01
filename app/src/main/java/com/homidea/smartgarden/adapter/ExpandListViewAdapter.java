package com.homidea.smartgarden.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.homidea.smartgarden.activity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wuyoude on 2016/1/29.
 */
public class ExpandListViewAdapter extends BaseExpandableListAdapter{

    Context context = null;
    List<HashMap<Object, Object>> groupList = new ArrayList<HashMap<Object, Object>>();
    List<List<HashMap<Object, Object>>> childList = new ArrayList<List<HashMap<Object, Object>>>();
    public ExpandListViewAdapter(Context context, List<HashMap<Object, Object>> groupList, List<List<HashMap<Object, Object>>> childList){
        this.context = context;
        this.groupList =groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_groupitem,null);
            viewHolder.groupText = (TextView)convertView.findViewById(R.id.gatewayName);
            viewHolder.groupImage = (ImageView)convertView.findViewById(R.id.img_logo);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        if (groupList.get(groupPosition).get("typeName").equals("arduino")){
            viewHolder.groupImage.setImageResource(R.drawable.arduino);
        }else if (groupList.get(groupPosition).get("typeName").equals("lw-board")){
            viewHolder.groupImage.setImageResource(R.drawable.lw_board);
        }else {
            viewHolder.groupImage.setImageResource(R.drawable.others);
        }
        viewHolder.groupText.setText(groupList.get(groupPosition).get("name").toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_childitem,null);
            viewHolder.childText = (TextView)convertView.findViewById(R.id.sensorName);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.childText.setText(childList.get(groupPosition).get(childPosition).get("name").toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    class ViewHolder{
        ImageView groupImage;
        TextView groupText;
        TextView childText;
    }
}
