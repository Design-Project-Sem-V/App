package com.umang_rathod.hms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
//                Pair pair = new Pair<View, String>(splashText, "screenTransition");
            //                    ActivityOptions options =  ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pair);
            startActivity(intent);
        },1000);

    }
}