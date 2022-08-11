package com.cvmaster.xosstech.Storyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cvmaster.xosstech.R;
import com.cvmaster.xosstech.SignUp.UserSignInPart1;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, UserSignInPart1.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}