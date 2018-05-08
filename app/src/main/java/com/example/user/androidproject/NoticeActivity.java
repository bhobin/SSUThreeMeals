package com.example.user.androidproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.user.androidproject.adapter.NoticeRecyclerAdapter;

/**
 * Created by user on 2016-09-02.
 */
public class NoticeActivity extends AppCompatActivity {
    private Context mContext;

    private ImageView mBtnBack;
    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;
    private NoticeRecyclerAdapter mRecyclerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        mContext = getApplicationContext();

        mRecycler = (RecyclerView) findViewById(R.id.recycler_notice);

        mBtnBack = (ImageView) findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initRecycler();

    }

    private void initRecycler() {
        mLayoutManager = new LinearLayoutManager(mContext);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(mLayoutManager);

        mRecyclerAdapter = new NoticeRecyclerAdapter(mContext);
        mRecycler.setAdapter(mRecyclerAdapter);
    }
}
