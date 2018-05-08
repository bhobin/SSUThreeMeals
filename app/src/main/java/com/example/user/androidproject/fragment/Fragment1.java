package com.example.user.androidproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.androidproject.MenuActivity1;
import com.example.user.androidproject.MenuActivity2;
import com.example.user.androidproject.MenuActivity3;
import com.example.user.androidproject.MenuActivity4;
import com.example.user.androidproject.MenuActivity5;
import com.example.user.androidproject.MenuActivity6;
import com.example.user.androidproject.R;

public class Fragment1 extends Fragment {

    ImageView background1, background2,background3, background4, background5, background6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        background1 = (ImageView) view.findViewById(R.id.background1);
        background2 = (ImageView) view.findViewById(R.id.background2);
        background3 = (ImageView) view.findViewById(R.id.background3);
        background4 = (ImageView) view.findViewById(R.id.background4);
        background5 = (ImageView) view.findViewById(R.id.background5);
        background6 = (ImageView) view.findViewById(R.id.background6);

        background1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuActivity1.class);
                intent.putExtra("data",getArguments());
                startActivity(intent);

            }
        });
        background2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuActivity2.class);
                intent.putExtra("data",getArguments());
                startActivity(intent);

            }
        });
        background3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuActivity3.class);
                intent.putExtra("data",getArguments());
                startActivity(intent);

            }
        });
        background4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuActivity4.class);
                intent.putExtra("data",getArguments());
                startActivity(intent);

            }
        });
        background5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuActivity5.class);
                intent.putExtra("data",getArguments());
                startActivity(intent);

            }
        });
        background6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MenuActivity6.class);
                intent.putExtra("data",getArguments());
                startActivity(intent);
            }
        });
        return view;
    }

}
