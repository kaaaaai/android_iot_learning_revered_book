package com.example.learningdemo.listviewupdate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.learningdemo.R;

import java.util.ArrayList;

public class MultiListActivity extends AppCompatActivity {

    private static final int TYPE_SOLIDER = 0;
    private static final int TYPE_POKEMON = 1;
    private ListView list_content;
    private ArrayList<Object> mData = null;
    private MutiLayoutAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_list);

        //数据准备：
        mData = new ArrayList<Object>();
        for(int i = 0;i < 20;i++){
            switch ((int)(Math.random() * 2)){
                case TYPE_SOLIDER:
                    mData.add(new pokemon(R.mipmap.pokemonpika,"皮卡丘" ,"一隻電氣鼠，全身的皮毛都是黃色的。牠的耳朵很長，尖端是黑色的。牠有小小的嘴巴，以及黑色的眼睛。"));
                    break;
                case TYPE_POKEMON:
                    mData.add(new solider(R.mipmap.soldier,"小黄人"));
                    break;
            }
        }

        list_content = (ListView) findViewById(R.id.multple_list_view);
        myAdapter = new MutiLayoutAdapter(MultiListActivity.this,mData);
        list_content.setAdapter(myAdapter);
    }
}
