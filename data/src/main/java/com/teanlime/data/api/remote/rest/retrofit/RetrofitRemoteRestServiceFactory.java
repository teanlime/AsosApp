package com.teanlime.data.api.remote.rest.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Generates the RetrofitRemoteRestService, which is the REST API this application
 * uses to communicate with network.
 * Uses Gson as chosen Json parser, although migration to Moshi is considered for future
 * Uses RxJava as a CallAdapter, which means that a rest call response will return
 * an Observable object, instead of standard Call object.
 */
public class RetrofitRemoteRestServiceFactory {

    private static final String DATE_AND_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String BASE_URL = "http://teanlime.com/";

    public RetrofitRemoteRestService create() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        final Gson gson = new GsonBuilder()
                .setDateFormat(DATE_AND_TIME_FORMAT)
                .create();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build();

        return retrofit.create(RetrofitRemoteRestService.class);
    }
}