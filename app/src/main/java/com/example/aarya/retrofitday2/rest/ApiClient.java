package com.example.aarya.retrofitday2.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiClient {

    public Retrofit getClient(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build();


        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("")
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        return retrofit;
    }
}
