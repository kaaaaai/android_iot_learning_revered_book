package com.example.learningdemo.listviewupdate;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.learningdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ListviewUpdateActivity extends AppCompatActivity implements View.OnClickListener{

    private Context mContext;
    private ListView list_one;
    private ListUpdateAdapter<solider> mAdapter = null;
    private List<solider> mData = null;

    private ListView list_pokemon;
    private ListUpdateAdapter<pokemon> pokemonAdapter = null;
    private List<pokemon> pokemonData = null;

    private Button btn_add;
    private int flag = 0;
    private Button btn_add2;
    private Button btn_remove;
    private Button btn_remove2;
    private solider mData_5 = null;   //用来临时放对象的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_update);
        mContext = ListviewUpdateActivity.this;

        bindViews();

        mData = new ArrayList<solider>();
        mAdapter = new ListUpdateAdapter<solider>((ArrayList)mData, R.layout.solider_item) {
            @Override
            public void bindView(ViewHolder holder, solider obj) {
                holder.setImageResource(R.id.img_icon,obj.getImgId());
                holder.setText(R.id.txt_content,obj.getContent());
            }
        };
        list_one.setAdapter(mAdapter);


        pokemonData = new ArrayList<pokemon>();
        pokemonAdapter = new ListUpdateAdapter<pokemon>((ArrayList)pokemonData, R.layout.pokemon_item) {
            @Override
            public void bindView(ViewHolder holder, pokemon obj) {
                holder.setText(R.id.text_name, obj.getpName());
                holder.setText(R.id.text_description, obj.getpDescription());
                holder.setImageResource(R.id.img_icon, obj.getpImage());
            }
        };
        list_pokemon.setAdapter(pokemonAdapter);
    }

    private void bindViews(){
        list_one = (ListView) findViewById(R.id.list_update);
        list_pokemon = (ListView) findViewById(R.id.pokemon_list_view);

        TextView txt_empty = (TextView) findViewById(R.id.txt_empty);
        txt_empty.setText("暂无数据~");
        list_one.setEmptyView(txt_empty);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_remove = (Button) findViewById(R.id.btn_remove);
        btn_remove2 = (Button) findViewById(R.id.btn_remove2);

        btn_add2 = (Button) findViewById(R.id.btn_add2);
        btn_add2.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        btn_remove2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                if(flag == 5){
                    mData_5 = new solider(R.mipmap.soldier,"小兵+1，当前小兵：x " + flag);
                    mAdapter.add(mData_5);
                }else if(flag < 5){
                    mAdapter.add(new solider(R.mipmap.soldier,"小兵+1，当前小兵：x " + flag));
                }else{
                    pokemonAdapter.add(new pokemon(R.mipmap.pokemonpika,"皮卡丘 x " + flag,"一隻電氣鼠，全身的皮毛都是黃色的。牠的耳朵很長，尖端是黑色的。牠有小小的嘴巴，以及黑色的眼睛。"));
                }
                flag++;
                break;
            case R.id.btn_add2:
                //position从0开始算的
                mAdapter.add(4,new solider(R.mipmap.wizardsolider,"小兵+1，当前小兵：x " + flag));
                break;
            case R.id.btn_remove:
            {
                mAdapter.remove(mData_5);
            }

                break;
            case R.id.btn_remove2:
                mAdapter.remove(2);
                break;
        }
    }
}
