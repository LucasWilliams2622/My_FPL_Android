package com.abc.myfplapplication.Service;

import com.abc.myfplapplication.Model.LichHoc;
import com.abc.myfplapplication.Model.LoginInfo;
import com.abc.myfplapplication.Model.News;
import com.abc.myfplapplication.Model.Notification;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIService {


    String base_link ="http://42.96.3.177:8080/";
    @FormUrlEncoded
    @POST("login")
    Call<LoginInfo> Login(@Field("username") String username, @Field("password") String password);

    @GET("lichhoc")
    Call<ArrayList<LichHoc>> GetLichHoc(@Header("Authorization") String token);

    @GET("lichhoc/homnay")
    Call<ArrayList<LichHoc>> GetLichHocHomNay(@Header("Authorization") String token);

    @GET("news")
    Call<ArrayList<Notification>> GetNews(@Header("Authorization") String token);

}
