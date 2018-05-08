package com.example.user.androidproject;

import android.app.FragmentManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.util.Random;


public class PayChoiceActivity extends AppCompatActivity {
    private ImageView[] bullet = new ImageView[6];
    private ImageButton revolver;
    private ImageView flash;
    private int total=6;
    private int flag, death, check;
    private TextView textView;
    private int opp=6;
    private ImageView btn_back;
    FragmentManager fm;

    MediaPlayer fire, misfire;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_choice);


        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(opp));


        Random random = new Random();
        death = random.nextInt(total);
        fire = MediaPlayer.create(this, R.raw.fired);
        misfire = MediaPlayer.create(this, R.raw.misfired);
        fm=getFragmentManager();

        bullet[0] = (ImageView) findViewById(R.id.bullet1);
        bullet[1] = (ImageView) findViewById(R.id.bullet2);
        bullet[2] = (ImageView) findViewById(R.id.bullet3);
        bullet[3] = (ImageView) findViewById(R.id.bullet4);
        bullet[4] = (ImageView) findViewById(R.id.bullet5);
        bullet[5] = (ImageView) findViewById(R.id.bullet6);
        revolver = (ImageButton) findViewById(R.id.revolver);

        for (int i = 0; i < total; i++) {
            bullet[i].setVisibility(View.VISIBLE);
        }

        revolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag != 1) {
                    if (check == death) {
                        fire.start();

                        new LovelyStandardDialog(PayChoiceActivity.this)
                                .setTopColorRes(R.color.colorPrimary)
                                .setButtonsColorRes(R.color.darkDeepOrange)
                                .setIcon(R.drawable.pay)
                                .setTitle("You have to pay!!!")
                                .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        finish();
                                    }})
                                .show();

                        flag = 1;
                    } else {
                        bullet[total - 1].setVisibility(View.INVISIBLE);
                        total--;
                        check++;
                        misfire.start();
                    }
                }


                textView = (TextView) findViewById(R.id.textView);
                textView.setText(String.valueOf(--opp));
            }
        });
    }
}
