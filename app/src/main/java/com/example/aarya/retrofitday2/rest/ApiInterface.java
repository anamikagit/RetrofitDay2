package com.example.aarya.retrofitday2.rest;

import com.example.aarya.retrofitday2.model.QuestPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("/topics")
    Call<List<QuestPojo>> getRequest();

    @POST("/topics")
    Call<String> postRequest(@Body QuestPojo questPojo);
}
