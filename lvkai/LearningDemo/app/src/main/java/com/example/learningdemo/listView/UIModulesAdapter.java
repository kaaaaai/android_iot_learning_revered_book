package com.example.learningdemo.listView;

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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.uimodules_item,parent,false);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.iv_uimodules);
        TextView txt_aName = (TextView) convertView.findViewById(R.id.tv_uimodules);
//        img_icon.setBackgroundResource(mData.get(position).getaIcon());
        txt_aName.setText(mModules.get(position).getUiName());
        return convertView;
    }
}
