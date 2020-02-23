package com.example.learningdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.learningdemo.listviewupdate.ListviewUpdateActivity;
import com.example.learningdemo.listviewupdate.MultiListActivity;
import com.example.learningdemo.manactivitylist.UIModules;
import com.example.learningdemo.manactivitylist.UIModulesAdapter;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ListView list_uimodules;
    private UIModulesAdapter mAdapter = null;
    private List<UIModules> mUIMoudles = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    void initData() {

        mContext = MainActivity.this;

        list_uimodules = (ListView) findViewById(R.id.list_view);
        mUIMoudles = new LinkedList<UIModules>();
        mUIMoudles.add(new UIModules("TextView"));
        mUIMoudles.add(new UIModules("EditText/RadioButton"));
        mUIMoudles.add(new UIModules("Button"));
        mUIMoudles.add(new UIModules("ImageView/ProgressBar/Data & Time组件"));
        mUIMoudles.add(new UIModules("RatingBarView/SeekBarView/Switch/ToggleButton"));
        mUIMoudles.add(new UIModules("ListView 数据更新问题"));
        mUIMoudles.add(new UIModules("ListView 多布局"));
        mUIMoudles.add(new UIModules("GridView "));
        mUIMoudles.add(new UIModules("Spinner 下拉多选框 "));


        mAdapter = new UIModulesAdapter((LinkedList<UIModules>)mUIMoudles, mContext);
        list_uimodules.setAdapter(mAdapter);

        list_uimodules.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(MainActivity.this, TextViewActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, EditTextActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ButtonActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, ImageViewActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, RatingBarActivity.class));
                        break;
                    case  5:
                        startActivity(new Intent(MainActivity.this, ListviewUpdateActivity.class));
                        break;
                    case  6:
                        startActivity(new Intent(MainActivity.this, MultiListActivity.class));
                        break;
                    case  7:
                        startActivity(new Intent(MainActivity.this, GridViewActivity.class));
                        break;
                    case  8:
                        startActivity(new Intent(MainActivity.this, SpinnerActivity.class));
                        break;
                }
            }
        });


//        Button btnToedittext = findViewById(R.id.btn_to_edittext);
//        btnToedittext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, EditTextActivity.class));
//            }
//        });

    }
}
