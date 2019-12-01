package com.example.learningdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ButtonActivity extends AppCompatActivity {

    private Button btnOne,btnTwo,btnThree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnTwo.getText().toString().equals("按钮不可用")){
                    btnOne.setEnabled(false);
                    btnTwo.setText("按钮可用");
                }else{
                    btnOne.setEnabled(true);
                    btnTwo.setText("按钮不可用");
                }
            }
        });

        btnThree = (Button) findViewById(R.id.btnThree);
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ButtonActivity.this,ImageViewActivity.class));
            }
        });


    }
}
