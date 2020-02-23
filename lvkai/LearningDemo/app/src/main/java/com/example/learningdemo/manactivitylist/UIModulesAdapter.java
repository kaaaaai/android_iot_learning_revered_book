package com.example.learningdemo.manactivitylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learningdemo.R;

import java.util.LinkedList;

public class UIModulesAdapter extends BaseAdapter {

    private LinkedList<UIModules> mModules;
    private Context mContext;

    public UIModulesAdapter(LinkedList<UIModules> mModules, Context mContext){
        this.mModules = mModules;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mModules.size();
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

        ViewHolder holder = null;
        //2.4.6 BaseAdapter优化 1.复用ConvertView
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.uimodules_item,parent,false);

            holder = new ViewHolder();
            holder.text_Name =  (TextView) convertView.findViewById(R.id.tv_uimodules);
            holder.img_icon = (ImageView) convertView.findViewById(R.id.iv_uimodules);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        holder.text_Name.setText(mModules.get(position).getUiName());
        return convertView;
    }

    //2.ViewHolder 重用组件
    static class  ViewHolder{
        ImageView img_icon;
        TextView text_Name;
    }
}

