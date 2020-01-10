package com.example.myapplication.api;

import com.example.myapplication.model.ApiRseponse;
import com.example.myapplication.model.Favourites;
import com.example.myapplication.model.Learn;
import com.example.myapplication.model.Learned;
import com.example.myapplication.model.Logsign;
import com.example.myapplication.model.Topic;
import com.example.myapplication.model.User;
import com.example.myapplication.model.postData;

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
    public Call<ApiRseponse<User>> login(@Body User acc);

    @POST("api/Login/logsign")
    public Call<ApiRseponse<Boolean>>logsign(@Body Logsign user);

    @POST("api/Login/getSign")
    public Call<User>getAcc(@Body User acc);

    @GET("api/Exam/getExam")
    public Call<List<User>> getUser(@Query("id") int id);

    @GET("api/Topic/getTopic")
    public Call<List<Topic>> getTopic();

    @GET("api/Exam/getExam")
    public Call<List<Learn>>getLearn(@Query("id") int id);

    @POST("api/PostData/testFavourites")
    public Call<ApiRseponse<Boolean>>Favourites(@Body Favourites like);

    @GET("api/Learned/getLearned")
    public Call<List<Learned>> getLearned(@Query("iduser") int iduser);

    @POST("api/PostData/postLearned")
    public Call<postData>postData(@Body postData post);

    @GET("api/Login/getId")
    public Call<List<User>>getId(@Query("user") String user);

    @POST("api/PostData/getFavourites")
    public Call<Favourites>getFavourites(@Body Favourites data);

    @POST("api/PostData/delFavourites")
    public Call<Favourites>ddelFavourites (@Body Favourites data);



//    @GET("api/Learned/getLearned")
//    public  Call<List<RecyclerBaseItem>>getLearned();
//    @GET("api/Learned/getLearned")
//    public  Call<List<RecyclerBaseItem>>getTopic();
}
