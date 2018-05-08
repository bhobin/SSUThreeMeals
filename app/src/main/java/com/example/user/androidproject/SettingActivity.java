package com.example.user.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by kwanghee on 2016-09-01.
 */
public class SettingActivity extends AppCompatActivity {

    LinearLayout layout1, layout2, layout3;
    private ImageView mBtnBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        layout1 = (LinearLayout) findViewById(R.id.setting_menu1);
        layout2 = (LinearLayout) findViewById(R.id.setting_menu2);
        layout3 = (LinearLayout) findViewById(R.id.setting_menu3);

        mBtnBack = (ImageView) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layout1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "준비중인 기능입니다", Toast.LENGTH_SHORT).show();
            }
        });

        layout2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });

        layout3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "준비중인 기능입니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

}


