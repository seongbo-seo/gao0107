package com.example.bottomnavigationwithfragment.retrofit;

import com.example.bottomnavigationwithfragment.StoreItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetrofitInterface {
    String url="https://jsonplaceholder.typicode.com";

    String kakaoUrl = "https://dapi.kakao.com";

    //String kakaoapikey = "KakaoAk 54dae3c66feb1b6fc832731c99ac47e1";
    @Headers("Authorization:KakaoAK 54dae3c66feb1b6fc832731c99ac47e1")
    @GET("/v2/local/search/address.json")
    Call<JsonObject> getAddress(@Query("query")String juso,@Query("page")int page,@Query("size")int size);

    @GET("/todos")
    Call<JsonArray> getData();

    @Headers("Authorization:KakaoAK 54dae3c66feb1b6fc832731c99ac47e1")
    @GET("/v2/local/geo/coord2address.json")
    Call<JsonObject> transform(@Query("x")String x,@Query("y")String y,@Query("input_coord")String coord);


}
