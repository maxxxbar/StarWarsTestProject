package com.example.starwars.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConnection {

    private Retrofit retrofit;
    private String BASE_URL = "https://swapi.dev/api/";

    private APIConnection() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public static final class APIConnectionHolder {
        public static final APIConnection HOLDER_INSTANCE = new APIConnection();
    }

    public static APIConnection getInstance() {
        return APIConnectionHolder.HOLDER_INSTANCE;
    }

    public RestAPI createGet() {
        return retrofit.create(RestAPI.class);
    }

}
