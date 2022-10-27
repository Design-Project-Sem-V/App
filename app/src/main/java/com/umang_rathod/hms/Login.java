package com.umang_rathod.hms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LinearLayout login = findViewById(R.id.login), signup = findViewById(R.id.signup);
        RelativeLayout loginBtn  = findViewById(R.id.login_btn);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair(login, "transition1");
                pairs[1] = new Pair(signup, "transition2");
                pairs[2] = new Pair(loginBtn, "transition3");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =  ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });


    }


    // Stop going to previous screen
    @Override
    public void onBackPressed(){}
}