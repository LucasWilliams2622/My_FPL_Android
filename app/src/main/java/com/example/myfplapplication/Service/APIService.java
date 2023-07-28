package com.example.myfplapplication.Service;

import com.example.myfplapplication.Model.LichHoc;
import com.example.myfplapplication.Model.LoginInfo;
import com.example.myfplapplication.Model.News;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {
    // http://localhost/TimeManage/API/GetList.php

    String base_link ="http://42.96.3.177:8080/";
    @FormUrlEncoded
    @POST("login")
    Call<LoginInfo> Login(@Field("username") String username, @Field("password") String password);

    @GET("lichhoc")
    Call<ArrayList<LichHoc>> GetLichHoc(@Header("Authorization") String token);

    @GET("lichhoc/homnay")
    Call<ArrayList<LichHoc>> GetLichHocHomNay(@Header("Authorization") String token);

    @GET("news")
    Call<ArrayList<News>> GetNews(@Header("Authorization") String token);

}
