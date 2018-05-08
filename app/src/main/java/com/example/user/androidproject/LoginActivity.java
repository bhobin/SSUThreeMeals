package com.example.user.androidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import static java.lang.Boolean.FALSE;

public class LoginActivity extends AppCompatActivity {

        private LoginButton loginButton;
        private ImageView CustomloginButton;
        private CallbackManager callbackManager;
        private ImageView startbutton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            FacebookSdk.sdkInitialize(getApplicationContext()); // SDK 초기화 (setContentView 보다 먼저 실행되어야합니다. 안그럼 에러납니다.)
            setContentView(R.layout.activity_login);
            callbackManager = CallbackManager.Factory.create();  //로그인 응답을 처리할 콜백 관리자

            CustomloginButton = (ImageView) findViewById(R.id.loginBtn);
            CustomloginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //LoginManager - 요청된 읽기 또는 게시 권한으로 로그인 절차를 시작합니다.
                    //if(CustomloginButton.getText().toString().equals("로그아웃")) {
                    //    LoginManager.getInstance().logOut();
                    //    CustomloginButton.setText("FACEBOOK LOGIN");
                    //}
                    //else {
                        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends"));
                        LoginManager.getInstance().registerCallback(callbackManager,
                                new FacebookCallback<LoginResult>() {
                                    @Override
                                    public void onSuccess(LoginResult loginResult) {
                                        Log.e("onSuccess", "onSuccess");
                                        Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        finish();
                                    }

                                    @Override
                                    public void onCancel() {
                                        Log.e("onCancel", "onCancel");

                                    }

                                    @Override
                                    public void onError(FacebookException exception) {
                                        Log.e("onError", "onError " + exception.getLocalizedMessage());
                                    }
                                });
                   // }
                }
            });

            startbutton = (ImageView) findViewById(R.id.startBtn);
            View.OnClickListener clicklistener= new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }
            };
            startbutton.setOnClickListener(clicklistener);

            SharedPreferences pre=getSharedPreferences("bool", MODE_PRIVATE);
            for(int i=1;i<=40;i++){
                int myInt = (pre.getBoolean(i+"",FALSE)) ? 1 : 0;
                CommonData.setCheck(i,myInt);
            }

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }
