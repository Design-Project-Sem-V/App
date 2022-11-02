package com.umang_rathod.hms;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiCalls {

//    @Headers("Accept: application/json")
    @GET("login_user")
    Call<String> checkUser(
            @Query("email") String email,
            @Query("password") String password
    );
}


//
//    @Query("email") String email,
//    @Query("password") String password