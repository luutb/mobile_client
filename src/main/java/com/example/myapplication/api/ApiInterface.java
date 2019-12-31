package com.example.myapplication.api;

import com.example.myapplication.model.ApiRseponse;
import com.example.myapplication.model.Learn;
import com.example.myapplication.model.Topic;
import com.example.myapplication.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/api/user")
    public Call<List<String>> getUser(@Query("id") String username);

    @GET("/api/pass")
    public Call<List<String>> getPass(@Query("id") int id);

    @POST("api/Login/login")
    public Call<ApiRseponse<Boolean>>login(@Body User acc);

    @GET("api/Exam/getExam")
    public Call<List<User>> getUser (@Query("id") int id);

    @GET("api/Topic/getTopic")
    public Call<List<Topic>> getTopic();

    @GET("api/Exam/getExam")
    public Call<List<Learn>> getLearn(@Query("id") int id);
}
