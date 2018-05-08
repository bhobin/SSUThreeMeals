package com.example.user.androidproject;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.yarolegovich.lovelydialog.LovelyStandardDialog;


public class RandomChoiceActivity extends AppCompatActivity {

    private ImageView wheel;
    private float init_angle = 0.0f;
    private ImageView btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_choice);

        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        wheel = (ImageView) findViewById(R.id.wheel);
        wheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWheelImage();


            }
        });


    }

    private int getRandom(int maxNumber) {
        return (int) (Math.random() * maxNumber);
    }

    /**
     * 이미지를 회전시킨다.
     */

    //TODO:수정시작
    private void onWheelImage() {
        runOnUiThread(new Runnable() {

            float fromAngle = init_angle + 720 + getRandom(360);
            float location = fromAngle%360;
            public void run() {
                // 회전수를 제어
                // 램덤 0~360 + 720 도 + 회전각



                // 로테이션 에니메이션 초기화.
                // 시작각, 종료각, 자기 원을 그리며 회전 옵션 (Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f)
                RotateAnimation rAnim = new RotateAnimation(init_angle, fromAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                // 초기 시작 각도를 update 한다.
                init_angle = fromAngle;
                // 지속시간 길수록 느려진다.

                rAnim.setDuration(3000);
                // 에니메이션이 종료된후 상태를 고정주는 옵션
                rAnim.setFillEnabled(true);
                rAnim.setFillAfter(true);
                // 회전을 한다.
                wheel.startAnimation(rAnim);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (location > 360) location = location - 360;

                        if (0 < location && location <= 60) {
                            new LovelyStandardDialog(RandomChoiceActivity.this)
                                    .setTopColorRes(R.color.colorPrimary)
                                    .setButtonsColorRes(R.color.darkDeepOrange)
                                    .setIcon(R.drawable.background5)
                                    .setTitle("오늘의 메뉴는 육류입니다.")
                                    .setMessage("게임을 종료하시겠습니까?")
                                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }})
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }
                        if (60 < location && location <= 120) {
                            new LovelyStandardDialog(RandomChoiceActivity.this)
                                    .setTopColorRes(R.color.colorPrimary)
                                    .setButtonsColorRes(R.color.darkDeepOrange)
                                    .setIcon(R.drawable.background6)
                                    .setTitle("오늘의 메뉴는 기타입니다.")
                                    .setMessage("게임을 종료하시겠습니까?")
                                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }})
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();

                        }
                        if (120 < location && location <= 180) {
                            new LovelyStandardDialog(RandomChoiceActivity.this)
                                    .setTopColorRes(R.color.colorPrimary)
                                    .setButtonsColorRes(R.color.darkDeepOrange)
                                    .setIcon(R.drawable.background1)
                                    .setTitle("오늘의 메뉴는 한식입니다.")
                                    .setMessage("게임을 종료하시겠습니까?")
                                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }})
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();

                        }
                        if (180 < location && location <= 240) {
                            new LovelyStandardDialog(RandomChoiceActivity.this)
                                    .setTopColorRes(R.color.colorPrimary)
                                    .setButtonsColorRes(R.color.darkDeepOrange)
                                    .setIcon(R.drawable.background2)
                                    .setTitle("오늘의 메뉴는 분식입니다.")
                                    .setMessage("게임을 종료하시겠습니까?")
                                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }})
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }
                        if (240 < location && location <= 300) {
                            new LovelyStandardDialog(RandomChoiceActivity.this)
                                    .setTopColorRes(R.color.colorPrimary)
                                    .setButtonsColorRes(R.color.darkDeepOrange)
                                    .setIcon(R.drawable.background3)
                                    .setTitle("오늘의 메뉴는 양식/패스트푸드입니다.")
                                    .setMessage("게임을 종료하시겠습니까?")
                                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }})
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();


                        }
                        if (300 < location && location <= 360) {
                            new LovelyStandardDialog(RandomChoiceActivity.this)
                                    .setTopColorRes(R.color.colorPrimary)
                                    .setButtonsColorRes(R.color.darkDeepOrange)
                                    .setIcon(R.drawable.background4)
                                    .setTitle("오늘의 메뉴는 중식/일식입니다.")
                                    .setMessage("게임을 종료하시겠습니까?")
                                    .setPositiveButton(android.R.string.ok, new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }})
                                    .setNegativeButton(android.R.string.no, null)
                                    .show();
                        }
                    }
                }, 3000);

            }
        });
    }
    //TODO:수정끝
}
