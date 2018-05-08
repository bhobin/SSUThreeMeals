package com.example.user.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 손영호 on 2015-12-04.
 */
public class TmpActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmp);

        Intent intent = new Intent(TmpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();


    }
}
