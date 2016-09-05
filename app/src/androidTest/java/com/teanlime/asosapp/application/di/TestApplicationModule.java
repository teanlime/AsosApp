package com.teanlime.asosapp.application.di;

import android.app.Application;

import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestApplicationModule extends ApplicationModule {

    public TestApplicationModule(Application application) {
        super(application);
    }

    @Singleton
    @Provides
    RetrofitRemoteRestService retrofitRemoteRestService() {
        return new TestRetrofitRemoteRestService();
    }
}