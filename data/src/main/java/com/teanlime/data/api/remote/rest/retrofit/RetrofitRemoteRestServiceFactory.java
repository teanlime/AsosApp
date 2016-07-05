package com.teanlime.data.api.remote.rest.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRemoteRestServiceFactory {

    private static final String BASE_URL = "https://dl.dropboxusercontent.com/";

    public RetrofitRemoteRestService create() {
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(RetrofitRemoteRestService.class);
    }
}
