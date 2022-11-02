package com.umang_rathod.hms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN = 2000;
    private Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView splashText = findViewById(R.id.app_name);

        anim = AnimationUtils.loadAnimation(this,R.anim.logo_anim);

        splashText.setAnimation(anim);


        new Handler().postDelayed(() -> {

            SharedPreferences loggedPreference = getSharedPreferences("DESIGNPROJECTSEMVUSERLOGGEDINORNOT",MODE_PRIVATE);
            boolean isLoggedIn = loggedPreference.getBoolean("isLoggedIn", false);

            if (isLoggedIn){
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                startActivity(intent);
            }
        },SPLASH_SCREEN);

    }
}