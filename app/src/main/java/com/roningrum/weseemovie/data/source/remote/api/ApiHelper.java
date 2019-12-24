package com.roningrum.weseemovie.data.source.remote.api;

import com.roningrum.weseemovie.data.source.remote.response.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
