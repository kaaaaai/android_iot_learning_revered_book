package com.example.learningdemo;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.learningdemo.listviewupdate.ListUpdateAdapter;
import com.example.learningdemo.listviewupdate.MutiLayoutAdapter;
import com.example.learningdemo.listviewupdate.pokemon;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    private Context mContext;
    private GridView grid_photo;
    private ListUpdateAdapter<pokemon> mAdapter = null;
    private ArrayList<pokemon> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        mContext = GridViewActivity.this;
        grid_photo = (GridView) findViewById(R.id.grid_photo);

        mData = new ArrayList<pokemon>();
        mData.add(new pokemon(R.mipmap.pokemonpika, "皮卡丘","皮卡丘"));
        mData.add(new pokemon(R.mipmap.xiaohuolong, "小火龙",""));
        mData.add(new pokemon(R.mipmap.jienigui, "杰尼龟",""));
        mData.add(new pokemon(R.mipmap.leikaqiu, "雷卡丘","雷卡丘"));
        mData.add(new pokemon(R.mipmap.kabishou, "卡比兽","卡比兽"));
        mData.add(new pokemon(R.mipmap.pangding, "胖丁","胖丁"));
        mData.add(new pokemon(R.mipmap.miaowazhognzi, "妙蛙种子","妙蛙种子"));
        mData.add(new pokemon(R.mipmap.quanjishou, "拳击手","拳击手"));

//        mAdapter = new MutiLayoutAdapter(mData, R.layout.item_grid_icon) {
////            @Override
////            public void bindView(ListUpdateAdapter.ViewHolder holder, Icon obj) {
////                holder.setImageResource(R.id.img_icon, obj.());
////                holder.setText(R.id.txt_icon, obj.getiName());
////            }
////        };

//        mAdapter = new ListUpdateAdapter<pokemon>((ArrayList)mData, R.layout.item_grid_icon) {
//            @Override
//            public void bindView(ViewHolder holder, Object obj) {
//                holder.setText(R.id.text_name,obj.getpName());
//                holder.setImageResource(R.id.img_icon, obj.getpImage());
//            }
//        };

        mAdapter = new ListUpdateAdapter<pokemon>((ArrayList)mData, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, pokemon obj) {
                holder.setText(R.id.text_name,obj.getpName());
                holder.setImageResource(R.id.img_icon, obj.getpImage());
            }
        };

        grid_photo.setAdapter(mAdapter);

        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
