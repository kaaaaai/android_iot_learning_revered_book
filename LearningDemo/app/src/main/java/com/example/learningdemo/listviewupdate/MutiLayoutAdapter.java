package com.example.learningdemo.listviewupdate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learningdemo.R;

import java.util.ArrayList;

public class MutiLayoutAdapter  extends BaseAdapter {
    //定义两个类别标志
    private static final int TYPE_SOLIDER = 0;
    private static final int TYPE_POKEMON = 1;
    private Context mContext;
    private ArrayList<Object> mData = null;


    public MutiLayoutAdapter(Context mContext,ArrayList<Object> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //多布局的核心，通过这个判断类别
    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof pokemon) {
            return TYPE_POKEMON;
        } else if (mData.get(position) instanceof solider) {
            return TYPE_SOLIDER;
        } else {
            return super.getItemViewType(position);
        }
    }

    //类别数目
    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolder1 holder1 = null;
        ViewHolder2 holder2 = null;
        if(convertView == null){
            switch (type){
                case TYPE_POKEMON:
                    holder1 = new ViewHolder1();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.pokemon_item, parent, false);
                    holder1.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
                    holder1.txt_aname = (TextView) convertView.findViewById(R.id.text_name);
                    holder1.txt_description = (TextView) convertView.findViewById(R.id.text_description);
                    convertView.setTag(R.id.Tag_POKEMON,holder1);
                    break;
                case TYPE_SOLIDER:
                    holder2 = new ViewHolder2();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.solider_item, parent, false);
                    holder2.txt_bname = (TextView) convertView.findViewById(R.id.txt_content);
                    holder2.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
                    convertView.setTag(R.id.Tag_SOLIDER,holder2);
                    break;
            }
        }else{
            switch (type){
                case TYPE_POKEMON:
                    holder1 = (ViewHolder1) convertView.getTag(R.id.Tag_POKEMON);
                    break;
                case TYPE_SOLIDER:
                    holder2 = (ViewHolder2) convertView.getTag(R.id.Tag_SOLIDER);
                    break;
            }
        }

        Object obj = mData.get(position);
        //设置下控件的值
        switch (type){
            case TYPE_POKEMON:
                pokemon app = (pokemon) obj;
                if(app != null){
                    holder1.img_icon.setImageResource(app.getpImage());
                    holder1.txt_aname.setText(app.getpName());
                    holder1.txt_description.setText(app.getpDescription());
                }
                break;
            case TYPE_SOLIDER:
                solider book = (solider) obj;
                if(book != null){
                    holder2.txt_bname.setText(book.getContent());
                    holder2.img_icon.setImageResource(book.getImgId());
                }
                break;
        }
        return convertView;
    }


    //两个不同的ViewHolder
    private static class ViewHolder1{
        ImageView img_icon;
        TextView txt_aname;
        TextView txt_description;
    }

    private static class ViewHolder2{
        ImageView img_icon;
        TextView txt_bname;
    }
}
