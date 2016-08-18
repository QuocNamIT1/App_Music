package com.example.nam.music.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.nam.music.R;



/**
 * Created by nam on 7/3/2016.
 */
public class SplashScreenActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            musicApplaication.getAuth().setbSplashScreen(true);
            startActivity(new Intent(SplashScreenActivity.this,MainActivity.class));
            finish();
            }
        }, 5000);
    }
}
