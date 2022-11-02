package com.umang_rathod.hms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        LinearLayout login = findViewById(R.id.login), signup = findViewById(R.id.signup);
        RelativeLayout signupBtn  = findViewById(R.id.submit);

        // Hooks
        TextView appName = findViewById(R.id.app_name), hello = findViewById(R.id.hello), welcomeText = findViewById(R.id.welcome_text);
        LinearLayout heading = findViewById(R.id.heading), loginBtn = findViewById(R.id.login);
        RelativeLayout emailContainer = findViewById(R.id.email_container), passwordContainer = findViewById(R.id.password_container),
                submitBtn = findViewById(R.id.submit);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                Pair[] pairs = new Pair[8];
                pairs[0] = new Pair(appName, "transition0");
                pairs[1] = new Pair(hello, "transition1");
                pairs[2] = new Pair(welcomeText, "transition2");
                pairs[3] = new Pair(heading, "transition3");
                pairs[4] = new Pair(emailContainer, "transition4");
                pairs[5] = new Pair(passwordContainer, "transition5");
                pairs[6] = new Pair(submitBtn, "transition6");
                pairs[7] = new Pair(loginBtn, "transition7");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =  ActivityOptions.makeSceneTransitionAnimation(Signup.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("DESIGNPROJECTSEMVUSERDETAILS",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", "Umang Rathod");
                editor.putString("email", "rathodumang@gmail.com");
                editor.putString("mobile", "9925385704");
                editor.putString("password", "Umang@2003");
                editor.putInt("birthdate", 13);
                editor.putInt("birthmonth", 01);
                editor.putInt("birthyear", 2003);
                editor.putString("gender", "Male");
                editor.commit();

                // Save that device is logged in
                SharedPreferences loggedPreference = getSharedPreferences("DESIGNPROJECTSEMVUSERLOGGEDINORNOT",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = loggedPreference.edit();
                editor1.putBoolean("isLoggedIn", true);
                editor1.commit();

                // Go to dashboard
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                startActivity(intent);
            }
        });

    }
}