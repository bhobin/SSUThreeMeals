package com.example.user.androidproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.androidproject.adapter.PictureAdapter;

import java.io.FileNotFoundException;
import java.io.IOException;


public class MyPictureActivity extends AppCompatActivity {

    Context mContext;

    private RecyclerView mRecyclerView;
    private PictureAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ImageView btn_back;

    String str;
    String[] str2,array;
    Bitmap[] bitmaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypicture);
        mContext = this;

        str = getIntent().getStringExtra("data");
        Log.e("tag(intent넘긴후)",str);
        array = str.split(",");
        Log.e("array size",array.length+"");
        bitmaps=new Bitmap[array.length];

        for(int i=0; i<array.length ; i++) {
            Uri uri = Uri.parse(array[i]);
            try {
                bitmaps[i] = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
/*
        for(int i=0; i<array.length ; i++) {
            str2[i] =array[i]; //에러
        }
*/

        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_picture);

        initRecycler();
    }

    private void initRecycler() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if(bitmaps == null)
                Toast.makeText(MyPictureActivity.this, "현재 등록된 이미지가 없습니다.\n 이미지를 등록해주세요.", Toast.LENGTH_SHORT).show();

        mAdapter = new PictureAdapter(this, bitmaps ,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

}