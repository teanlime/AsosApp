package com.teanlime.asosapp.application.di;

import android.app.Application;
import android.content.Context;

import com.teanlime.asosapp.utils.di.dagger.qualifier.context.ApplicationContext;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestService;
import com.teanlime.data.api.remote.rest.retrofit.RetrofitRemoteRestServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    protected final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    Context context() {
        return application;
    }

    @Singleton
    @Provides
    RetrofitRemoteRestService retrofitRemoteRestService() {
        return new RetrofitRemoteRestServiceFactory().create();
    }
}