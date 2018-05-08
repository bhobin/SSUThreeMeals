package com.example.user.androidproject.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.androidproject.CommonData;
import com.example.user.androidproject.R;
import com.example.user.androidproject.StoreData;
import com.example.user.androidproject.adapter.StoreAdapter;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by user on 2016-11-04.
 */
public class Fragment2 extends Fragment {

    private Context mContext;

    private RecyclerView mRecyclerView;
    private StoreAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private ArrayList<StoreData> mDataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        mContext = (Context)getActivity();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_fragment2);

        Bundle bundle = getArguments();
        mDataList = bundle.getParcelableArrayList("data");
        initRecycler2();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        initRecycler2();
    }
/*
    private void initRecycler() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);



        ArrayList<StoreData> tmpDataList = new ArrayList<>();

        for (int i = 0 ; i<mDataList.size() ; i++){
            if(true){
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


        mAdapter = new StoreAdapter(mContext,tmpDataList,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }
*/

    private void initRecycler2() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);


        Comparator<StoreData> comparator = new Comparator<StoreData>()
        {
            Collator collator = Collator.getInstance();

            @Override
            public int compare(StoreData object1, StoreData object2)
            {
                return object1.getId() < object2.getId() ? -1 : object1.getId() > object2.getId() ? 1:0;
            }
        };
        Collections.sort(mDataList, comparator);


        ArrayList<StoreData> tmpDataList = new ArrayList<>();


        for (int i = 1 ; i<=mDataList.size() ; i++){
            if(CommonData.getCheck(i)==1){
                tmpDataList.add(mDataList.get(i-1));
            }
        }
/*
        for (int i = mDataList.size() ; i>=1 ; i--){
            if(CommonData.getCheck(i)==1){
                tmpDataList.add(mDataList.get(i));
            }
        }
*/
        mAdapter = new StoreAdapter(mContext,tmpDataList,mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }
}
