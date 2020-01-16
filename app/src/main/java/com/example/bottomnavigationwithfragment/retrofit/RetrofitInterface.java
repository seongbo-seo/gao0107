package com.example.bottomnavigationwithfragment.retrofit;

import com.example.bottomnavigationwithfragment.StoreItem;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    String url="https://jsonplaceholder.typicode.com";
    @GET("/todos")
    Call<JsonArray> getData();


}
