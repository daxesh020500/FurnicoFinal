package com.example.furnico.retrofit.networkmanager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilderService {
    private static Retrofit instance;

    private RetrofitBuilderService(){}
    public static Retrofit getInstance(){
        if(instance==null){
            synchronized (RetrofitBuilderService.class){
                if(instance==null){
                    instance = new Retrofit.Builder().baseUrl("http://10.177.68.61:8085/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(new OkHttpClient()).build();
                }
            }
        }
        return instance;
    }
}
