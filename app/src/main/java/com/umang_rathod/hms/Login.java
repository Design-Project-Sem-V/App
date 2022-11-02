package com.umang_rathod.hms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.JsonReader;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    //For mongodb database
    private ApiCalls apiCalls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText emailTxt = findViewById(R.id.email), passwordTxt = findViewById(R.id.password);
        String email1 = emailTxt.getText().toString();
        String password1 = passwordTxt.getText().toString();


        // Hooks
        TextView appName = findViewById(R.id.app_name), hello = findViewById(R.id.hello), welcomeText = findViewById(R.id.welcome_text);
        LinearLayout heading = findViewById(R.id.heading), signupBtn = findViewById(R.id.signup);
        RelativeLayout emailContainer = findViewById(R.id.email_container), passwordContainer = findViewById(R.id.password_container),
                submitBtn = findViewById(R.id.submit);




        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://design-project-sem-v.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("localhost:3000/")
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();

        apiCalls = retrofit.create(ApiCalls.class);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                Pair[] pairs = new Pair[8];
                pairs[0] = new Pair(appName, "transition0");
                pairs[1] = new Pair(hello, "transition1");
                pairs[2] = new Pair(welcomeText, "transition2");
                pairs[3] = new Pair(heading, "transition3");
                pairs[4] = new Pair(emailContainer, "transition4");
                pairs[5] = new Pair(passwordContainer, "transition5");
                pairs[6] = new Pair(submitBtn, "transition6");
                pairs[7] = new Pair(signupBtn, "transition7");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =  ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
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

    private void validateUser(String email, String password) {
        Call<String> call = apiCalls.checkUser("umang@gmail.com", "Umang@2003");

//        TextView tempview = findViewById(R.id.temp);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(Login.this, "ERROR", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(Login.this, response.body().toString(), Toast.LENGTH_SHORT).show();
//                tempview.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
//                tempview.setText(t.toString());
                Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    // Stop going to previous screen
    @Override
    public void onBackPressed(){}
}