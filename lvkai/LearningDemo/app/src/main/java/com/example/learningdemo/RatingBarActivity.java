package com.example.learningdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class RatingBarActivity extends AppCompatActivity {

    private RatingBar rb_normal;
    private SeekBar sb_normal;
    private TextView txt_cur;
    private Context mContext;

    private ToggleButton tbtn_open;
    private Switch swh_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

        mContext = RatingBarActivity.this;

        rb_normal = (RatingBar) findViewById(R.id.rb_normal);
        rb_normal.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RatingBarActivity.this, "rating:" + String.valueOf(rating),
                        Toast.LENGTH_LONG).show();
            }
        });


        sb_normal = (SeekBar) findViewById(R.id.sb_normal);
        txt_cur = (TextView) findViewById(R.id.textSb);
        sb_normal.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txt_cur.setText("当前进度值:" + progress + "  / 100 ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(mContext, "触碰SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(mContext, "放开SeekBar", Toast.LENGTH_SHORT).show();
            }
        });

        tbtn_open = (ToggleButton) findViewById(R.id.tbtn_open);
        tbtn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tbtn_open.isChecked()) Toast.makeText(mContext,"打开声音",Toast.LENGTH_SHORT).show();
                else Toast.makeText(mContext,"打开声音",Toast.LENGTH_SHORT).show();
            }
        });
        swh_status = (Switch) findViewById(R.id.swh_status);
//        tbtn_open.setOnCheckedChangeListener(this);
        swh_status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()) Toast.makeText(mContext,"开关:ON",Toast.LENGTH_SHORT).show();
                else Toast.makeText(mContext,"开关:OFF",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
