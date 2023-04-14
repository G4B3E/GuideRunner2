package com.example.guiderunner2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3050;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashScreenActivation();
    }
    public void splashScreenActivation(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("Important", Context.MODE_PRIVATE);
                String Token = sharedPreferences.getString("token",null);
                if (Token != null){
                    Intent intent = new Intent(SplashScreen.this, BottomNav.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
                else {
                    Intent intent = new Intent(SplashScreen.this, OpenScreenMenu.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        }, SPLASH_TIME_OUT);
    }
}