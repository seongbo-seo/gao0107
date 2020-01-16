package com.example.bottomnavigationwithfragment.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConnection {
    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(RetrofitInterface.url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
