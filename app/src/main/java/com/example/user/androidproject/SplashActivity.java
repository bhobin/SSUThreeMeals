package com.example.user.androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

        Animation trans_up = AnimationUtils.loadAnimation(getBaseContext(),R.anim.traslate_up);
        Animation alpha = AnimationUtils.loadAnimation(getBaseContext(),R.anim.alpha);

        ImageView text = (ImageView)findViewById(R.id.splash_logo);
        //ImageView image = (ImageView)findViewById(R.id.splash_image);

        ProgressBar progress = (ProgressBar)findViewById(R.id.splash_progress);

        text.setVisibility(View.INVISIBLE);
       // image.setVisibility(View.INVISIBLE);


        text.startAnimation(trans_up);
       // image.startAnimation(alpha);


    }
}