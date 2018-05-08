package com.example.user.androidproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.user.androidproject.adapter.StoreAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MenuActivity4 extends AppCompatActivity {

    Context mContext;

    private RecyclerView mRecyclerView;
    private StoreAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private ImageView btn_back;
    private ArrayList<StoreData> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list4);

        mContext = this;
        mDataList = getIntent().getBundleExtra("data").getParcelableArrayList("data");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_menu_list4);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initRecycler();
        //initView();
    }

    private void initRecycler() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<StoreData> tmpDataList = new ArrayList<>();
        for (int i = 0 ; i<mDataList.size() ; i++){
            if(mDataList.get(i).getCategory().equals("중식/일식")){
                tmpDataList.add(mDataList.get(i));
            }
        }

        Comparator<StoreData> comparator = new Comparator<StoreData>()
        {
            Collator collator = Collator.getInstance();

            @Override
            public int compare(StoreData object1, StoreData object2)
            {
                return collator.compare(object1.getTitle(), object2.getTitle());
            }
        };

        Collections.sort(tmpDataList, comparator);

        mAdapter = new StoreAdapter(this,tmpDataList,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }
}