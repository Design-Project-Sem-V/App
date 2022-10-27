package com.umang_rathod.hms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        LinearLayout login = findViewById(R.id.login), signup = findViewById(R.id.signup);
        RelativeLayout signupBtn  = findViewById(R.id.signup_btn);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair(login, "transition1");
                pairs[1] = new Pair(signup, "transition2");
                pairs[2] = new Pair(signupBtn, "transition3");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =  ActivityOptions.makeSceneTransitionAnimation(Signup.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }
}